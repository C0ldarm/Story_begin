package chnu.edu.inno.config;

import jakarta.servlet.http.HttpServletRequest;
import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.CommitPropertiesProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * audit-configuration для JaVers + MongoDB.
 * <p>
 * Сам об'єкт Javers і сховище (колекції jv_snapshots, jv_head_id) створює
 * javers-spring-boot-starter-mongo; налаштування javers.* лежать в application.properties.
 * Тут визначено метадані аудиту (audit_metadata):
 * <ul>
 *   <li>автор коміту - заголовок запиту {@value #AUTHOR_HEADER} (без нього "anonymous",
 *       поза HTTP-запитом, наприклад при старті, - "system");</li>
 *   <li>властивості коміту - тип сутності, HTTP-метод, шлях, IP клієнта.</li>
 * </ul>
 */
@Configuration
@EnableAspectJAutoProxy // потрібен для @JaversSpringDataAuditable на репозиторії
public class AuditConfiguration {

    public static final String AUTHOR_HEADER = "X-Author";

    @Bean
    public AuthorProvider authorProvider() {
        return new HeaderAuthorProvider();
    }

    @Bean
    public CommitPropertiesProvider commitPropertiesProvider() {
        return new RequestCommitPropertiesProvider();
    }

    private static Optional<HttpServletRequest> currentRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes servletAttributes) {
            return Optional.of(servletAttributes.getRequest());
        }
        return Optional.empty();
    }

    static class HeaderAuthorProvider implements AuthorProvider {
        @Override
        public String provide() {
            return currentRequest()
                    .map(request -> request.getHeader(AUTHOR_HEADER))
                    .filter(author -> !author.isBlank())
                    .orElseGet(() -> currentRequest().isPresent() ? "anonymous" : "system");
        }
    }

    static class RequestCommitPropertiesProvider implements CommitPropertiesProvider {

        @Override
        public Map<String, String> provideForCommittedObject(Object domainObject) {
            return metadata(domainObject);
        }

        // Ті самі метадані для видалення (без @Override: підстраховка щодо сигнатури в різних версіях JaVers)
        public Map<String, String> provideForDeletedObject(Object domainObject) {
            return metadata(domainObject);
        }

        private Map<String, String> metadata(Object domainObject) {
            Map<String, String> properties = new LinkedHashMap<>();
            properties.put("entity", domainObject == null ? "unknown" : domainObject.getClass().getSimpleName());
            currentRequest().ifPresentOrElse(request -> {
                properties.put("httpMethod", request.getMethod());
                properties.put("path", request.getRequestURI());
                properties.put("clientIp", String.valueOf(request.getRemoteAddr()));
            }, () -> properties.put("source", "system"));
            return properties;
        }
    }
}
