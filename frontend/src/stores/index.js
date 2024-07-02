// store/index.js
import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import config from '@/config';

export default new Vuex.Store({
    state: {
        currentUser: null,
        currentAudio: null,
    },
    mutations: {
        setCurrentUser(state, user) {
            state.currentUser = user;
        },
        logout(state) {
            console.log('logged out');
            state.currentUser = null;
        },
        setCurrentAudio(state, audio) {
            state.currentAudio = audio;
        },
    },
    actions: {
        async fetchCurrentUser({ commit }) {
            if (localStorage.getItem('currentUserId') == null) return;
            try {
                const response = await axios.get(`${config.apiUrl}/profile?idUser=${localStorage.getItem('currentUserId')}`);
                const user = await response.data;
                commit('setCurrentUser', user);
            } catch (error) {
                console.error('Error fetching current user:', error);
            }
        },
        logout({ commit }) {
            localStorage.removeItem('token');
            localStorage.removeItem('currentUserId');
            commit('logout');
        },
    },
    getters: {
        currentUser: (state) => state.currentUser,
        isLoggedIn: (state) => !!state.currentUser,
        currentAudio: (state) => state.currentAudio,
    },
});
