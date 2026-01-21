import { NavLink, Outlet } from "react-router-dom";

export default function AdminLayout() {
    return (
        <div style={{ display: "flex", minHeight: "100vh" }}>
            <aside style={{ width: 240, borderRight: "1px solid #eee", padding: 16 }}>
                <h3 style={{ marginTop: 0 }}>Admin</h3>

                <nav style={{ display: "flex", flexDirection: "column", gap: 8 }}>
                    <NavLink to="/admin/products">Products</NavLink>
                    <NavLink to="/admin/purchase-orders">Purchase Orders</NavLink>
                    <NavLink to="/admin/sales">Sales</NavLink>
                </nav>
            </aside>

            <main style={{ flex: 1, padding: 24 }}>
                <Outlet />
            </main>
        </div>
    );
}
