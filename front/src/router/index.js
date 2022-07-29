import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/home/HomeView.vue'

import DiaryCreateView from '../views/diary/DiaryCreateView.vue'

import LoginView from '@/views/account/LoginView.vue'
import SignUpView from '@/views/account/SignUpView.vue'
import SignUpOptionView from '@/views/account/SignUpOptionView.vue'
import PasswordFindView from '@/views/account/PasswordFindView.vue'
import PasswordChangeView from '@/views/account/PasswordChangeView.vue'

// import ProfileView from '@/views/profile/ProfileView'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/diary/create',
    name: 'diaryCreate',
    component: DiaryCreateView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'signUp',
    component: SignUpView
  },
  {
    path: '/signup/option',
    name: 'signUpOption',
    component: SignUpOptionView
  },
  {
    path: '/passwordfind',
    name: 'passwordFind',
    component: PasswordFindView
  },
  {
    path: '/passwordchange',
    name: 'passwordChange',
    component: PasswordChangeView
  },

  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
