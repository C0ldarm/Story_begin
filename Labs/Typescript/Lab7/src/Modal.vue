<template>
    <div v-if="isOpen" class="modal-overlay">
      <div class="modal-content">
        <h2>Edit Participant</h2>
        <form @submit.prevent="updateParticipant">
          <input v-model="participant.name" placeholder="Name" required />
          <input v-model="participant.email" placeholder="Email" required />
          <input v-model="participant.phone" placeholder="Phone" required />
          <input v-model="participant.birthdate" type="date" required />
          <button type="submit">Update Data</button>
          <button type="button" @click="close">Cancel</button>
        </form>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { ref } from 'vue';
  
  export default {
    props: {
      isOpen: Boolean,
      participant: Object,
    },
    emits: ['update', 'close'],
    setup(props, { emit }) {
      const participant = ref({ ...props.participant });
  
      const updateParticipant = () => {
        emit('update', participant.value);
        close();
      };
  
      const close = () => {
        emit('close');
      };
  
      return { participant, updateParticipant, close };
    },
  };
  </script>
  
  <style scoped>
  .modal-overlay {
    /* Add styles for the modal overlay */
  }
  .modal-content {
    /* Add styles for the modal content */
  }
  </style>
  