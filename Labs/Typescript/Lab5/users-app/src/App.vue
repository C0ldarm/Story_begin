<template>
  <div id="app">
    <h1>User Profiles</h1>
    <div class="toolbar">
      <button @click="filterByGender('male')">Male</button>
      <button @click="filterByGender('female')">Female</button>
      <button @click="filterByGender('all')">All</button>
    </div>
    <div class="user-list">
      <UserCard
        v-for="user in filteredUsers"
        :key="user.id"
        :user="user"
      />
      <p v-if="filteredUsers.length === 0">Список юзерів пустий</p>
    </div>
  </div>
</template>

<script>
import UserCard from './components/UserCard.vue';

export default {
  name: "App",
  components: {
    UserCard,
  },
  data() {
    return {
      users: [
        { id: 1, firstName: "John", lastName: "Doe", gender: "Male", age: 30, position: "Developer", photo: "https://via.placeholder.com/150", hobbies: ["Coding", "Hiking", "Reading"] },
        { id: 2, firstName: "Jane", lastName: "Smith", gender: "Female", age: 17, position: "Student", photo: "https://via.placeholder.com/150", hobbies: ["Painting", "Dancing"] },
        { id: 3, firstName: "Mike", lastName: "Johnson", gender: "Male", age: 22, position: "Designer", photo: "https://via.placeholder.com/150", hobbies: ["Sketching", "Traveling"] },
        { id: 4, firstName: "Emily", lastName: "Davis", gender: "Female", age: 25, position: "Product Manager", photo: "https://via.placeholder.com/150", hobbies: ["Cooking", "Yoga"] },
        { id: 5, firstName: "David", lastName: "Brown", gender: "Male", age: 10, position: "Architect", photo: "https://via.placeholder.com/150", hobbies: ["Photography", "Cycling"] },
        { id: 6, firstName: "Sophia", lastName: "Martinez", gender: "Female", age: 20, position: "Marketing Specialist", photo: "https://via.placeholder.com/150", hobbies: ["Blogging", "Running"] },
        { id: 7, firstName: "James", lastName: "Garcia", gender: "Male", age: 35, position: "Engineer", photo: "https://via.placeholder.com/150", hobbies: ["Gaming", "Fishing"] },
        { id: 8, firstName: "Mia", lastName: "Wilson", gender: "Female", age: 29, position: "Data Scientist", photo: "https://via.placeholder.com/150", hobbies: ["Reading", "Traveling"] },
        { id: 9, firstName: "Daniel", lastName: "Lee", gender: "Male", age: 40, position: "CTO", photo: "https://via.placeholder.com/150", hobbies: ["Investing", "Collecting"] },
        { id: 10, firstName: "Olivia", lastName: "Clark", gender: "Female", age: 32, position: "HR Manager", photo: "https://via.placeholder.com/150", hobbies: ["Gardening", "Volunteering"] },
      ],
      selectedGender: 'all',
    };
  },
  computed: {
    filteredUsers() {
      // Ensure gender comparison is case-sensitive to match user data
      if (this.selectedGender === 'all') {
        return this.users;
      }
      return this.users.filter(user => user.gender.toLowerCase() === this.selectedGender.toLowerCase());
    },
  },
  methods: {
    filterByGender(gender) {
      this.selectedGender = gender === 'all' ? 'all' : gender.charAt(0).toUpperCase() + gender.slice(1).toLowerCase();
    },
  },
};
</script>

<style>
.toolbar {
  margin-bottom: 20px;
}

.toolbar button {
  margin-right: 10px;
  padding: 10px;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}

.toolbar button:hover {
  background-color: #0056b3;
}

.user-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
</style>
