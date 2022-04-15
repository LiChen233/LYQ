// eslint-disable-next-line
import {UserLayout, BasicLayout, BlankLayout} from '@/layouts'
import {bxAnaalyse} from '@/core/icons'

const RouteView = {
  name: 'RouteView',
  render: h => h('router-view')
}

const my = [
  {
    path: '/ClazzManager',
    name: 'ClazzManager',
    component: () => import('@/views/clazz/ClazzManager'),
    meta: {title: '班级管理'}
  },
  {
    path: '/WorkManager',
    name: 'WorkManager',
    component: () => import('@/views/work/WorkManager'),
    meta: {title: '作业管理'}
  },
  {
    path: '/MyWork',
    name: 'MyWork',
    component: () => import('@/views/work/MyWork'),
    meta: {title: '我的作业'}
  },
  {
    path: '/DoWork',
    name: 'DoWork',
    component: () => import('@/views/work/DoWork'),
    meta: {title: '写作业'}
  },
  {
    path: '/SubjectManager',
    name: 'SubjectManager',
    component: () => import('@/views/subject/SubjectManager'),
    meta: {title: '科目管理'}
  },
  {
    path: '/NoticeManager',
    name: 'NoticeManager',
    component: () => import('@/views/notice/NoticeManager'),
    meta: {title: '公告管理'}
  },
  {
    path: '/WorkAddEdit',
    name: 'WorkAddEdit',
    component: () => import('@/views/work/WorkAddEdit'),
    meta: {title: '作业发布'}
  },
  {
    path: '/Timetable',
    name: 'Timetable',
    component: () => import('@/views/timetable/Timetable'),
    meta: {title: '我的课表'}
  },
  {
    path: '/TimetableManager',
    name: 'TimetableManager',
    component: () => import('@/views/timetable/TimetableManager'),
    meta: {title: '课表管理'}
  },
]

const menus = [
  {
    path: '/system',
    redirect: '/system/menu-manage',
    component: RouteView,
    meta: {title: '系统管理'},
    children: [
      {
        path: '/system/menu-manage',
        name: 'MenuManage',
        component: () => import('@/views/system/menu/MenuManage'),
        meta: {title: '菜单管理'}
      },
      {
        path: '/system/user-manage',
        name: 'UserManage',
        component: () => import('@/views/system/user/UserManage'),
        meta: {title: '用户管理'}
      },
      {
        path: '/system/role-manage',
        name: 'RoleManage',
        component: () => import('@/views/system/role/RoleManage'),
        meta: {title: '角色权限管理'}
      },
      {
        path: '/system/tag-manage',
        name: 'TagManage',
        component: () => import('@/views/system/tag/TagManage'),
        meta: {title: '标签管理'}
      },
    ]
  },
]

export const asyncRouterMap = [
  {
    path: '/',
    name: 'index',
    component: BasicLayout,
    meta: {title: 'menu.home'},
    redirect: '/dashboard/workplace',
    children: [
      // dashboard
      {
        path: '/dashboard',
        name: 'dashboard',
        redirect: '/dashboard/workplace',
        component: RouteView,
        meta: {title: 'menu.dashboard', keepAlive: true, icon: bxAnaalyse, permission: ['dashboard']},
        children: [
          {
            path: '/dashboard/analysis/:pageNo([1-9]\\d*)?',
            name: 'Analysis',
            component: () => import('@/views/dashboard/Analysis'),
            meta: {title: 'menu.dashboard.analysis', keepAlive: false, permission: ['dashboard']}
          },
          {
            path: '/dashboard/workplace',
            name: 'Workplace1',
            component: () => import('@/views/dashboard/Workplace1'),
            meta: {title: 'menu.dashboard.workplace', keepAlive: true, permission: ['dashboard']}
          },
        ]
      },
      // Exception
      {
        path: '/exception',
        name: 'exception',
        component: RouteView,
        redirect: '/exception/403',
        meta: {title: 'menu.exception', icon: 'warning', permission: ['exception']},
        children: [
          {
            path: '/exception/403',
            name: 'Exception403',
            component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/403'),
            meta: {title: 'menu.exception.not-permission', permission: ['exception']}
          },
          {
            path: '/exception/404',
            name: 'Exception404',
            component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404'),
            meta: {title: 'menu.exception.not-find', permission: ['exception']}
          },
          {
            path: '/exception/500',
            name: 'Exception500',
            component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/500'),
            meta: {title: 'menu.exception.server-error', permission: ['exception']}
          }
        ]
      },

      // account
      {
        path: '/account',
        component: RouteView,
        redirect: '/account/center',
        name: 'account',
        meta: {title: 'menu.account', icon: 'user', keepAlive: true, permission: ['user']},
        children: [
          {
            path: '/account/center',
            name: 'center',
            component: () => import('@/views/account/center'),
            meta: {title: 'menu.account.center', keepAlive: true, permission: ['user']}
          },
          {
            path: '/account/settings',
            name: 'settings',
            component: () => import('@/views/account/settings/Index'),
            meta: {title: 'menu.account.settings', hideHeader: true, permission: ['user']},
            redirect: '/account/settings/basic',
            hideChildrenInMenu: true,
            children: [
              {
                path: '/account/settings/basic',
                name: 'BasicSettings',
                component: () => import('@/views/account/settings/BasicSetting'),
                meta: {title: 'account.settings.menuMap.basic', hidden: true, permission: ['user']}
              },
              {
                path: '/account/settings/security',
                name: 'SecuritySettings',
                component: () => import('@/views/account/settings/Security'),
                meta: {
                  title: 'account.settings.menuMap.security',
                  hidden: true,
                  keepAlive: true,
                  permission: ['user']
                }
              },
              {
                path: '/account/settings/custom',
                name: 'CustomSettings',
                component: () => import('@/views/account/settings/Custom'),
                meta: {title: 'account.settings.menuMap.custom', hidden: true, keepAlive: true, permission: ['user']}
              },
              {
                path: '/account/settings/binding',
                name: 'BindingSettings',
                component: () => import('@/views/account/settings/Binding'),
                meta: {title: 'account.settings.menuMap.binding', hidden: true, keepAlive: true, permission: ['user']}
              },
              {
                path: '/account/settings/notification',
                name: 'NotificationSettings',
                component: () => import('@/views/account/settings/Notification'),
                meta: {
                  title: 'account.settings.menuMap.notification',
                  hidden: true,
                  keepAlive: true,
                  permission: ['user']
                }
              }
            ]
          }
        ]
      },
      ...menus,
      ...my
    ]
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
      },
      {
        path: 'recover',
        name: 'recover',
        component: undefined
      }
    ]
  },

  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404')
  }
]
