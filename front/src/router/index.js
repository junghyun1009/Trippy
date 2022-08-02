import store from '../store'

import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/home/HomeView.vue'
import SearchView from '@/views/home/SearchView.vue'

import DiaryCreateView from '../views/diary/DiaryCreateView.vue'
import DiaryDetailView from '../views/diary/DiaryDetailView.vue'
import DiaryEditView from '../views/diary/DiaryEditView.vue'
import DiaryCommentView from '../views/diary/DiaryCommentView.vue'

import LoginView from '@/views/account/LoginView.vue'
import SignUpView from '@/views/account/SignUpView.vue'
import SignUpOptionView from '@/views/account/SignUpOptionView.vue'
import PasswordFindView from '@/views/account/PasswordFindView.vue'
import PasswordChangeView from '@/views/account/PasswordChangeView.vue'

import ProfileView from '@/views/profile/ProfileView.vue'
import ProfileEditView from '@/views/profile/ProfileEditView.vue'

import CommunityCreateView from '@/views/community/CommunityCreateView.vue'
import CommunityView from '@/views/community/CommunityView.vue'
import CommunityDetailView from '@/views/community/CommunityDetailView.vue'
import CommunityEditView from '@/views/community/CommunityEditView.vue'

import BadgeListView from '../views/badge/BadgeListView.vue'

import NotFound404View from '@/views/handler/NotFound404View.vue'

const routes = [
  // 모두가 접근 가능한 route
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/search',
    name: 'search',
    component: SearchView
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
    path: '/passwordchange',
    name: 'passwordChange',
    component: PasswordChangeView
  },
  {
    path: '/passwordfind',
    name: 'passwordFind',
    component: PasswordFindView
  },
  {
    path: '/404',
    name: 'notFound404',
    component: NotFound404View
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/404'
  }, 


  // login한 사용자만 접근 가능한 route
  {
    path: '/diary/create',
    name: 'diaryCreate',
    component: DiaryCreateView
  },
  {
    path: '/diary',
    // 나중에 pk 추가하기
    name: 'diaryDetail',
    component: DiaryDetailView
  },
  {
    path: '/diary/edit',
    // 나중에 pk 추가하기
    name: 'diaryEdit',
    component: DiaryEditView
  },
  {
    path: '/diary/comment',
    // 나중에 pk 추가하기
    name: 'diaryComment',
    component: DiaryCommentView
  },


  {
    path: '/profile',
    // 나중에 pk 추가하기
    name: 'profile',
    component: ProfileView
  },
  {
    path: '/profile/edit',
    // 나중에 pk 추가하기
    name: 'profileEdit',
    component: ProfileEditView
  },


  {
    path: '/community',
    name: 'community',
    component: CommunityView
  },

  {
    path: '/community/:communityId',
    name: 'communityDetail',
    component: CommunityDetailView
  },
  {
    path: '/community/create',
    name: 'communityCreate',
    component: CommunityCreateView
  },
  {
    path: '/community/edit',
    name: 'communityEdit',
    component: CommunityEditView
  },

  
  {
    path: '/badge',
    name: 'badgeList',
    component: BadgeListView
  },
  

  
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from, next) => {
  const { isLoggedIn } = store.getters

  const authPages = [
    'diaryCreate', 'diaryEdit', 'diaryDetail', 'diaryComment',
    'profile', 'profileEdit', 
    'community', 'communityEdit', 'communityDetail', 'communityCreate',
    'badgeList'
  ]

  const isAuthRequired = authPages.includes(to.name)
  
  if ( isAuthRequired && !isLoggedIn ) {
    alert('로그인을 해주세요!')
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router