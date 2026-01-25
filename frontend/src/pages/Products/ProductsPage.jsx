import {useEffect, useState} from "react";
import ProductCreateForm from "./_components/ProductCreateForm.jsx";
import {useNavigate} from "react-router-dom";

export default function ProductsPage() {
    const navigate = useNavigate();

    const [items, setItems] = useState([]);
    const [pageInfo, setPageInfo] = useState({page: 0, size: 20, totalPages: 0, hasNext: false});
    const [pending, setPending] = useState(false);
    const [error, setError] = useState("");


    const fetchProducts = async (page = 0) => {
        setPending(true);
        setError("");

        const res = await fetch(`/products?page=${page}&size=${pageInfo.size}&sort=createdAt,desc`);
        const json = await res.json();
        const data = json.data;

        setItems(data.item);
        setPageInfo({page: data.page, size: data.size, totalPages: data.totalPages, hasNext: data.hasNext});

        setPending(false);
    };

    const createProduct = async ({name, salePrice, costPrice}) => {
        setPending(true);
        setError("");

        const res = await fetch("/products", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({name, salePrice, costPrice}),
        });
        const json = await res.json();

        if (!res.ok || json?.success === false) {
            setError(json?.message ?? "등록 실패");
            setPending(false);
            return;
        }

        await fetchProducts(0);
        setPending(false);
    };

    useEffect(() => {
        fetchProducts(0);
    }, []);

    return (
        <div style={{padding: 16}}>
            <h2>상품</h2>

            <ProductCreateForm pending={pending} onCreate={createProduct}/>

            {error && <p style={{color: "crimson"}}>{error}</p>}
            {pending && <p>로딩중...</p>}

            <table border="1" cellPadding="8" style={{width: "100%", borderCollapse: "collapse"}}>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>이름</th>
                    <th>판매가</th>
                    <th>원가</th>
                </tr>
                </thead>
                <tbody>
                {items.map((p) => (
                    <tr key={p.id}>
                        <td>{p.id}</td>
                        <td>
                            <button
                                type="button"
                                onClick={() => navigate(`/admin/products/${p.id}`)}
                                style={{
                                    background: "none",
                                    border: "none",
                                    padding: 0,
                                    color: "blue",
                                    cursor: "pointer"
                                }}
                            >
                                {p.name}
                            </button>
                        </td>
                        <td>{Number(p.salePrice).toLocaleString()}</td>
                        <td>{Number(p.costPrice).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div style={{marginTop: 12, display: "flex", gap: 8, alignItems: "center"}}>
                <button onClick={() => fetchProducts(pageInfo.page - 1)} disabled={pending || pageInfo.page === 0}>
                    이전
                </button>

                <span>
          {pageInfo.page + 1} / {pageInfo.totalPages}
        </span>

                <button onClick={() => fetchProducts(pageInfo.page + 1)} disabled={pending || !pageInfo.hasNext}>
                    다음
                </button>
            </div>
        </div>
    );
}
