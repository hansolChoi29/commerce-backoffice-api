import ProductsPage from "../pages/Products/ProductsPage";
import PurchaseOrdersPage from "../pages/PurchaseOrders/PurchaseOrdersPage";
import SalesPage from "../pages/Sales/SalesPage";
import {Navigate, Route, Router, Routes} from "react-router-dom";
import AdminLayout from "../pages/layouts/AdminLayout.jsx";
import AuthLayout from "../pages/layouts/AuthLayout.jsx";
import LoginPage from "../pages/Auth/LoginPage.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";

export default function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" replace/>}/>

            {/* 인증 화면 */}
            <Route element={<AuthLayout/>}>
                <Route path="/login" element={<LoginPage/>}/>
            </Route>

            {/* 보호 구간 */}
            <Route element={<ProtectedRoute/>}>
                <Route path="/admin" element={<AdminLayout/>}>
                    <Route index element={<Navigate to="products" replace/>}/>
                    <Route path="products" element={<ProductsPage/>}/>
                    <Route path="purchase-orders" element={<PurchaseOrdersPage/>}/>
                    <Route path="sales" element={<SalesPage/>}/>
                </Route>
            </Route>

            <Route path="*" element={<div>404</div>}/>
        </Routes>
    )
}