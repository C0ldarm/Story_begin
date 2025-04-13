<template>
  <div class="container mx-auto p-6 bg-gray-900 text-white min-h-screen">
    <h1 class="text-4xl font-bold text-center mb-8 main-label">Lottery App</h1>

    <!-- Winners Block -->
    <div class="bg-gray-800 shadow-lg rounded-lg p-4 mb-6">
      <h2 class="text-2xl font-semibold mb-4">Winners</h2>
      <ul class="list-disc pl-5 mb-4">
        <li v-for="(winner, index) in winners" :key="index" class="text-lg">{{ winner.name }}</li>
      </ul>
      <button 
        class="mt-2 p-2 bg-blue-500 text-white rounded hover:bg-blue-600 disabled:bg-gray-400 transition duration-200"
        @click="selectWinner"
        :disabled="winners.length >= 3 || participants.length === 0"
      >
        New Winner
      </button>
    </div>

    <!-- Registration Form -->
    <div class="bg-gray-800 shadow-lg rounded-lg p-4 mb-6">
      <h2 class="text-2xl font-semibold mb-4">Register Participant</h2>
      <form @submit.prevent="addParticipant">
        <div class="mb-4">
          <label for="name" class="block text-gray-300">Name:</label>
          <input
            v-model="newParticipant.name"
            type="text"
            id="name"
            class="bg-gray-700 border border-gray-600 p-2 w-full rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div class="mb-4">
          <label for="email" class="block text-gray-300">Email:</label>
          <input
            v-model="newParticipant.email"
            type="email"
            id="email"
            class="bg-gray-700 border border-gray-600 p-2 w-full rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
            :class="{'border-red-500': !validEmail}"
            required
          />
          <p v-if="!validEmail" class="text-red-500 text-sm">Invalid email format</p>
        </div>
        <div class="mb-4">
          <label for="phone" class="block text-gray-300">Phone:</label>
          <input
            v-model="newParticipant.phone"
            type="text"
            id="phone"
            class="bg-gray-700 border border-gray-600 p-2 w-full rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
            :class="{'border-red-500': !validPhone}"
            required
          />
          <p v-if="!validPhone" class="text-red-500 text-sm">Invalid phone format</p>
        </div>
        <div class="mb-4">
          <label for="birthdate" class="block text-gray-300">Birthdate:</label>
          <input
            v-model="newParticipant.birthdate"
            type="date"
            id="birthdate"
            class="bg-gray-700 border border-gray-600 p-2 w-full rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
            :class="{'border-red-500': !validBirthdate}"
            required
          />
          <p v-if="!validBirthdate" class="text-red-500 text-sm">Date cannot be in the future</p>
        </div>
        <button
          type="submit"
          class="p-2 bg-green-500 text-white rounded-lg shadow hover:bg-green-600 transition duration-200"
          :disabled="!validForm"
        >
          Save
        </button>
      </form>
    </div>

    <!-- Participants Table -->
    <div class="bg-gray-800 shadow-lg rounded-lg p-4">
      <h2 class="text-2xl font-semibold mb-4">Participants</h2>
      <SearchBar @filter-by-name="filterParticipants" />
      <table class="table-auto w-full border-collapse border border-gray-200">
        <thead class="bg-gray-700">
          <tr>
            <th class="border p-2 text-left">Name</th>
            <th class="border p-2 text-left">Email</th>
            <th class="border p-2 text-left">Phone</th>
            <th class="border p-2 text-left">Birthdate</th>
            <th class="border p-2 text-left">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(participant, index) in filteredParticipants" :key="index" class="hover:bg-gray-600 transition duration-200">
            <td class="border p-2">{{ participant.name }}</td>
            <td class="border p-2">{{ participant.email }}</td>
            <td class="border p-2">{{ participant.phone }}</td>
            <td class="border p-2">{{ participant.birthdate }}</td>
            <td class="border p-2">
              <button @click="openEditModal(participant)" class="bg-yellow-500 text-white rounded p-1 hover:bg-yellow-600">Edit</button>
              <button @click="openDeleteModal(participant)" class="bg-red-500 text-white rounded p-1 hover:bg-red-600">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <Modal
      :isOpen="isEditModalOpen"
      :participant="selectedParticipant"
      @update="updateParticipant"
      @close="isEditModalOpen = false"
    />

    <DeleteModal
      :isOpen="isDeleteModalOpen"
      :participant="selectedParticipant"
      @delete="deleteParticipant"
      @close="isDeleteModalOpen = false"
    />
  </div>
</template>

<script lang="ts" setup>
import { ref, computed, onMounted } from 'vue';
import Modal from './Modal.vue';
import DeleteModal from './DeleteModal.vue';
import SearchBar from './SearchBar.vue';

interface Participant {
  name: string;
  email: string;
  phone: string;
  birthdate: string;
}

const participants = ref<Participant[]>([]);
const winners = ref<Participant[]>([]);
const newParticipant = ref<Participant>({
  name: '',
  email: '',
  phone: '',
  birthdate: '',
});

// Validation
const validEmail = computed(() =>
  /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(newParticipant.value.email)
);

const validPhone = computed(() =>
  /^\+?[1-9]\d{1,14}$/.test(newParticipant.value.phone)
);

const validBirthdate = computed(() => {
  const birthdate = new Date(newParticipant.value.birthdate);
  const today = new Date();
  return birthdate <= today;
});

const validForm = computed(() =>
  newParticipant.value.name &&
  validEmail.value &&
  validPhone.value &&
  validBirthdate.value
);

const filteredParticipants = ref<Participant[]>([]);

const loadParticipants = () => {
  const storedParticipants = localStorage.getItem('participants');
  if (storedParticipants) {
    participants.value = JSON.parse(storedParticipants);
    filteredParticipants.value = [...participants.value];
  }
};

const saveParticipants = () => {
  localStorage.setItem('participants', JSON.stringify(participants.value));
};

const emailExists = (email: string) => {
  return participants.value.some((participant) => participant.email === email);
};

function addParticipant() {
  if (emailExists(newParticipant.value.email)) {
    alert('Email already exists!');
    return;
  }
  participants.value.push({ ...newParticipant.value });
  saveParticipants();
  newParticipant.value = { name: '', email: '', phone: '', birthdate: '' };
  filteredParticipants.value = [...participants.value]; // Update filtered list
}

function selectWinner() {
  if (participants.value.length > 0 && winners.value.length < 3) {
    const randomIndex = Math.floor(Math.random() * participants.value.length);
    const winner = participants.value[randomIndex];
    winners.value.push(winner);
  }
}

const isEditModalOpen = ref(false);
const isDeleteModalOpen = ref(false);
const selectedParticipant = ref<Participant | null>(null);

const openEditModal = (participant: Participant) => {
  selectedParticipant.value = participant;
  isEditModalOpen.value = true;
};

const updateParticipant = (updatedParticipant: Participant) => {
  const index = participants.value.indexOf(selectedParticipant.value as Participant);
  participants.value[index] = updatedParticipant;
  saveParticipants();
};

const openDeleteModal = (participant: Participant) => {
  selectedParticipant.value = participant;
  isDeleteModalOpen.value = true;
};

const deleteParticipant = (participant: Participant) => {
  participants.value = participants.value.filter((p) => p !== participant);
  saveParticipants();
};

const filterParticipants = (searchTerm: string) => {
  filteredParticipants.value = participants.value.filter((p) =>
    p.name.toLowerCase().includes(searchTerm.toLowerCase())
  );
};

onMounted(loadParticipants);
</script>

<style scoped>
.main-label {
  text-shadow: 0 0 10px #ffffff, 0 0 20px #ffffff, 0 0 30px #00ffcc;
}
</style>
