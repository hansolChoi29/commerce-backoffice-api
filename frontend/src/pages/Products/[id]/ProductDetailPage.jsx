import {useEffect, useState} from "react";
import {useParams, useNavigate} from "react-router-dom";
import {deleteProductApi, fetchProductApi, updateProductApi} from "../../../api/products.api.js";
import ProductEditForm from "./_components/ProductEditForm.jsx";
import ProductDeleteBox from "./_components/ProductDeleteBox.jsx";

export default function ProductDetailPage() {
    const {id} = useParams();
    const navigate = useNavigate();

    const [item, setItem] = useState(null);
    const [pending, setPending] = useState(false);
    const [error, setError] = useState("");

    // view | edit | delete
    const [mode, setMode] = useState("view");

    // 완료 문구
    const [notice, setNotice] = useState("");

    const load = async () => {
        setPending(true);
        setError("");
        try {
            const data = await fetchProductApi(id);
            setItem(data);
        } catch (e) {
            setError(e.message ?? "조회 실패");
        } finally {
            setPending(false);
        }
    };

    useEffect(() => {
        load();
        setMode("view");
    }, [id]);

    // 드로어 열릴 때 배경 스크롤 막기 (UX)
    useEffect(() => {
        if (mode === "edit") {
            const prev = document.body.style.overflow;
            document.body.style.overflow = "hidden";
            return () => {
                document.body.style.overflow = prev;
            };
        }
    }, [mode]);

    const showNotice = (msg) => {
        setNotice(msg);
        window.clearTimeout(showNotice._t);
        showNotice._t = window.setTimeout(() => setNotice(""), 2000);
    };

    const handleSave = async (payload) => {
        setPending(true);
        setError("");
        try {
            await updateProductApi(id, payload);

            await load();

            setMode("view");
            showNotice("수정이 완료되었습니다.");
        } catch (e) {
            setError(e.message ?? "수정 실패");
        } finally {
            setPending(false);
        }
    };

    const handleDelete = async () => {
        setPending(true);
        setError("");
        try {
            await deleteProductApi(id);
            navigate("/admin/products");
        } catch (e) {
            setError(e.message ?? "삭제 실패");
        } finally {
            setPending(false);
        }
    };

    if (pending && !item) return <div style={{padding: 16}}>로딩중...</div>;
    if (error) return <div style={{padding: 16, color: "crimson"}}>{error}</div>;
    if (!item) return <div style={{padding: 16}}>데이터 없음</div>;

    const isEditOpen = mode === "edit";

    return (
        <div style={{padding: 16}}>
            {/* 완료 문구 배너 */}
            {notice && (
                <div
                    style={{
                        position: "fixed",
                        top: 16,
                        left: "50%",
                        transform: "translateX(-50%)",
                        background: "#111",
                        color: "#fff",
                        padding: "10px 14px",
                        borderRadius: 10,
                        zIndex: 2000,
                        boxShadow: "0 6px 20px rgba(0,0,0,0.25)",
                    }}
                >
                    {notice}
                </div>
            )}

            <button type="button" onClick={() => navigate(-1)}>뒤로</button>

            <h2>상품명: {item.name}</h2>
            <div>상품코드: {item.sku}</div>
            <div>상태: {item.status}</div>
            <div>판매가: {Number(item.salePrice).toLocaleString()}</div>
            <div>원가: {Number(item.costPrice).toLocaleString()}</div>
            <div>등록일: {item.createdAt}</div>

            <div style={{display: "flex", gap: 8, marginTop: 12}}>
                <button type="button" onClick={() => setMode("edit")} disabled={pending}>
                    수정
                </button>
                <button
                    type="button"
                    onClick={() => setMode("delete")}
                    disabled={pending}
                    style={{color: "crimson"}}
                >
                    삭제
                </button>
            </div>

            {mode === "delete" && (
                <ProductDeleteBox pending={pending} onDelete={handleDelete}/>
            )}

            <div
                style={{
                    position: "fixed",
                    inset: 0,
                    background: "rgba(0,0,0,0.35)",
                    opacity: isEditOpen ? 1 : 0,
                    pointerEvents: isEditOpen ? "auto" : "none",
                    transition: "opacity 180ms ease",
                    zIndex: 999,
                }}
            />

            <div
                style={{
                    position: "fixed",
                    top: 0,
                    right: 0,
                    height: "100vh",
                    width: 420,
                    maxWidth: "90vw",
                    background: "#fff",
                    borderLeft: "1px solid #e5e5e5",
                    padding: 16,
                    overflow: "auto",
                    zIndex: 1000,

                    transform: isEditOpen ? "translateX(0)" : "translateX(100%)",
                    transition: "transform 220ms ease",
                    boxShadow: "-10px 0 30px rgba(0,0,0,0.12)",
                }}
                aria-hidden={!isEditOpen}
            >
                <div style={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                    <h3 style={{margin: 0}}>{item.name} </h3>
                    <button type="button" onClick={() => setMode("view")} disabled={pending}>
                        닫기
                    </button>
                </div>

                <ProductEditForm
                    item={item}
                    pending={pending}
                    onSave={handleSave}
                    onCancel={() => setMode("view")}
                />
            </div>
        </div>
    );
}
