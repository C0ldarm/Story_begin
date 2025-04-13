<template>
    <div v-if="isOpen" class="modal-overlay">
      <div class="modal-content">
        <p>Are you sure you want to delete {{ participant.name }}?</p>
        <button @click="confirmDelete">Yes</button>
        <button @click="close">No</button>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  export default {
    props: {
      isOpen: Boolean,
      participant: Object,
    },
    emits: ['delete', 'close'],
    setup(props, { emit }) {
      const confirmDelete = () => {
        emit('delete', props.participant);
        close();
      };
  
      const close = () => {
        emit('close');
      };
  
      return { confirmDelete, close };
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
  