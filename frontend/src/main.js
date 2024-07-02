import { createApp } from 'vue';
import App from './App.vue';
import router from './router/index';
import vuetify from './plugins/vuetify';
import config from './config';

import VueFileAgentNext from '@boindil/vue-file-agent-next'

import '@boindil/vue-file-agent-next/dist/vue-file-agent-next.css'
import '@mdi/font/css/materialdesignicons.css';
import './assets/styles/global.scss';
import store from "@/stores";

const app = createApp(App);

app.config.globalProperties.$config = config;

app.use(store);
app.use(router);
app.use(vuetify);
app.use(VueFileAgentNext);

app.mount('#app');
