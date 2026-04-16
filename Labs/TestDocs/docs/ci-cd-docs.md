# Documentation CI/CD Pipeline

## Артефакти, що генеруються автоматично
- OpenAPI специфікація + Swagger UI
- Storybook (UI-компоненти)
- Статичний сайт документації (MkDocs)

## Тригери
- Push у гілку `feature/*` або `develop`
- Merge (Pull Request) у `main`

## Послідовність кроків (GitHub Actions)

1. **Validate**
   - Перевірка OpenAPI специфікації (spectral або openapi-validator)
   - Lint Markdown файлів

2. **Build**
   - Build Swagger UI
   - Build Storybook
   - Build MkDocs site

3. **Test**
   - Перевірка, що Swagger UI збирається без помилок
   - Перевірка, що Storybook збирається успішно

4. **Deploy**
   - При merge у `main`:
     - Публікація Swagger UI та MkDocs на GitHub Pages
     - Публікація Storybook на GitHub Pages (`/storybook`)

## Структура URL (GitHub Pages)
- https://your-org.github.io/ems-docs/ → головний сайт документації
- https://your-org.github.io/ems-docs/api/ → Swagger UI
- https://your-org.github.io/ems-docs/storybook/ → Storybook

## Політика версійності
- Версія документації завжди відповідає версії продукту (semver)
- При breaking change в API → збільшується major версія документації
- Стабільні версії документації зберігаються в тегах (v1.0, v1.1, v2.0)