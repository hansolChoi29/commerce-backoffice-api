import { Navigate, Outlet } from "react-router-dom";

export default function ProtectedRoute() {
    const isAuthed = !!localStorage.getItem("accessToken"); // 일단 임시(나중에 상태관리로 교체)
    return isAuthed ? <Outlet /> : <Navigate to="/login" replace />;
}
