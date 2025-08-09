import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { dataService } from './services/dataService'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

async function initApp() {
  try {

    await dataService.reloadAllData()
    console.log('✅ App initialized with data loaded')
  } catch (error) {
    console.error('❌ Error loading initial data:', error)
  } finally {

    createApp(App).use(router).mount('#app')
  }
}

initApp()
