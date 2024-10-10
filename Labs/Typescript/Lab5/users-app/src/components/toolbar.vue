<template>
    <div class="toolbar">
      <button @click="setFilter('all')">All</button>
      <button @click="setFilter('male')">Male</button>
      <button @click="setFilter('female')">Female</button>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    name: 'Toolbar',
    emits: ['filter'],
    setup(props, { emit }) {
      const setFilter = (gender: 'all' | 'male' | 'female') => {
        emit('filter', gender);
      };
  
      return {
        setFilter
      };
    }
  });
  </script>
  
  <style scoped>
  .toolbar {
    margin-bottom: 20px;
  }
  
  .toolbar button {
    margin-right: 10px;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    background-color: #007bff;
    color: #fff;
    transition: background-color 0.3s;
  }
  
  .toolbar button:hover {
    background-color: #0056b3;
  }
  </style>
  