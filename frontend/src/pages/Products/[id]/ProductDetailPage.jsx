import {useEffect, useState} from "react";
import {useParams, useNavigate} from "react-router-dom";
import {deleteProductApi, fetchProductApi, fetchProductsApi, updateProductApi} from "../../../api/products.api.js";
import ProductEditForm from "./_components/ProductEditForm.jsx";
import ProductDeleteBox from "./_components/ProductDeleteBox.jsx";

export default function ProductDetailPage() {
    const {id} = useParams();
    const navigate = useNavigate();

    const [item, setItem] = useState(null);
    const [pending, setPending] = useState(false);
    const [error, setError] = useState("");

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
    }, [id]);

    const handleSave = async (payload) => {
        setPending(true);
        setError("");
        try {
            const updated = await updateProductApi(id, payload);
            setItem(updated); // 화면 즉시 반영
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
            navigate("/admin/products"); // 삭제 후 목록으로
        } catch (e) {
            setError(e.message ?? "삭제 실패");
        } finally {
            setPending(false);
        }
    };

    if (pending && !item) return <div style={{padding: 16}}>로딩중...</div>;
    if (error) return <div style={{padding: 16, color: "crimson"}}>{error}</div>;
    if (!item) return <div style={{padding: 16}}>데이터 없음</div>;

    return (
        <div style={{padding: 16}}>
            <button type="button" onClick={() => navigate(-1)}>뒤로</button>
            <h2>상품명: {item.name}</h2>

            <div>상품코드: {item.sku}</div>
            <div>상태: {item.status}</div>
            <div>판매가: {Number(item.salePrice).toLocaleString()}</div>
            <div>원가: {Number(item.costPrice).toLocaleString()}</div>
            <div>등록일: {item.createdAt}</div>

            <ProductEditForm item={item} pending={pending} onSave={handleSave}/>
            <ProductDeleteBox pending={pending} onDelete={handleDelete}/>
        </div>
    );
}
