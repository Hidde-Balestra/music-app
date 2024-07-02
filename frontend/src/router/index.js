import {createRouter, createWebHistory} from 'vue-router';
import Home from '../views/Home';
import Registration from '../views/Registration';
import Login from '../views/Login';
import Profile from "@/views/Profile";
import Search from "@/views/Search.vue";
import Admin from "@/views/Admin.vue";
import Terms from "@/views/Terms.vue";

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'home',
        component: Home,
    },
    {
        path: '/search/:search',
        name: 'search',
        component: Search,
    },
    {
        path: '/register',
        name: 'register',
        component: Registration,
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
    },
    {
        path: '/profile/:id',
        name: 'profile',
        component: Profile,
        // meta: {requiresAuth: true}, // This route requires authentication
    },
    {
        path: '/admin',
        name: 'admin',
        component: Admin,
        meta: { requiresAuth: true }, // This route requires authentication
    },
    {
        path: '/terms-and-conditions',
        name: 'terms',
        component: Terms,
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

// Add navigation guard to check if the route requires authentication
router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth) {
        const token = localStorage.getItem('token');
        let isLoggedIn = false;

        if (token != null) {
            let base64Url = token.split('.')[1];
            let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            let jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            isLoggedIn = (JSON.parse(jsonPayload).exp * 1000) > Date.now();
        }

        if (isLoggedIn) {
            next();
        } else {
            // Redirect to the login page if not authenticated
            next({name: 'login'});
        }
    } else {
        // Continue to the next route if it doesn't require authentication
        next();
    }
});

export default router;
