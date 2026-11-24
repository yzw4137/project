import Layout from "@/layout/index.vue";
import type { RouteRecordRaw } from "vue-router";
// 引入tabBarRoutes
import tabBarRoutes from "./tabBarRoutes";
// 引入otherRoutes
import otherRoutes from "./otherRoutes";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "root",
    component: Layout,
    redirect: "/search",
    children: [
      // tabBar页面
      ...tabBarRoutes,
      //  非tabBar页面
      ...otherRoutes
    ]
  },
  // 未匹配到路由时重定向到找房页，避免白屏
  {
    path: "/:pathMatch(.*)*",
    redirect: "/search"
  }
];

export default routes;
