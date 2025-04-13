<template>
    <div class="user-card" :style="{ backgroundColor: cardColor }">
      <img :src="user.photo" alt="User Photo" class="user-photo" />
      <h2>{{ user.firstName }} {{ user.lastName }}</h2>
      <p>Position: {{ user.position }}</p>
      <p v-if="user.age > 18">Age: {{ user.age }}</p>
      <p>Gender: {{ user.gender }}</p>
      <h3>Hobbies:</h3>
      <ul>
        <li v-for="(hobby, index) in user.hobbies" :key="index">{{ hobby }}</li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: "UserCard",
    props: {
      user: {
        type: Object,
        required: true,
      },
    },
    computed: {
      cardColor() {
        // Change color based on age (customize colors as per your preference)
        return this.user.age > 18 ? "#d4edda" : "#f8d7da"; // Light green for adults, light red for minors
      },
    },
  };
  </script>
  
  <style scoped>
  .user-card {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 16px;
    margin: 16px;
    text-align: center;
    width: 200px;
    transition: background-color 0.3s;
  }
  .user-photo {
    width: 100%;
    border-radius: 50%;
  }
  </style>
  