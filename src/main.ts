import { createApp, onMounted } from 'vue'
import { createPinia } from 'pinia'
import router from './router'
import App from './App.vue'

const app = createApp(App);

const loadPinia = async () => {
    const pinia =  createPinia();
    app.use(pinia);
    app.use(router);
    await import("@/access");
}

loadPinia();
app.mount('#app')



