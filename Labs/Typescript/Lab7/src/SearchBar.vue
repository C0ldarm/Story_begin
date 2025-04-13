<template>
    <div>
      <input
        v-model="searchTerm"
        @input="$emit('filter-by-name', searchTerm)"
        placeholder="Search by name"
      />
    </div>
  </template>
  
  <script lang="ts">
  import { ref } from 'vue';
  
  export default {
    emits: ['filter-by-name'],
    setup(_, { emit }) {
      const searchTerm = ref('');
  
      return { searchTerm };
    },
  };
  </script>
  