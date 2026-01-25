import {useEffect, useState} from "react";
import {useParams, useNavigate} from "react-router-dom";

export default function ProductDetailPage() {
    const {id} = useParams();
    const navigate = useNavigate();

    const [item, setItem] = useState(null);
    const [pending, setPending] = useState(false);

    useEffect(() => {
        const fetchOne = async () => {
            setPending(true);
            const res = await fetch(`/products/${id}`);
            const json = await res.json();
            setItem(json.data);
            setPending(false);
        };
        fetchOne();
    }, [id]);

    if (pending) return <div style={{padding: 16}}>로딩중...</div>;
    if (!item) return <div style={{padding: 16}}>데이터 없음</div>;

    return (
        <div style={{padding: 16}}>
            <button type="button" onClick={() => navigate(-1)}>뒤로</button>
            <h2>상품명: {item.name}</h2>

            {/*<div>ID: {item.id}</div>*/}
            <div>상품코드: {item.sku}</div>
            {/*판매 가능, 판매 중지*/}
            <div>상태: {item.status}</div>
            <div>판매가: {Number(item.salePrice).toLocaleString()}</div>
            <div>원가: {Number(item.costPrice).toLocaleString()}</div>
            <div>등록일: {item.createdAt}</div>
        </div>
    );
}
