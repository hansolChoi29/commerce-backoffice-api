import {useEffect, useState} from "react";

export default function ProductsPage() {
    const [items, setItem] = useState([]);
    const [pageInfo, setPageInfo] = useState({
        page: 0,
        size: 20,
        totalPages: 0,
        hasNext: false
    });

    const [pending, setPending] = useState(false);

    const fetchProducts = async (page = 0) => {
        setPending(true);
        const res = await fetch(`/products?page=${page}&size=${pageInfo.size}&sort=createdAt,desc`);
        const json = await res.json();
        const data = json.data;
        setItem(data.item);
        setPageInfo({
            page: data.page,
            size: data.size,
            totalPages: data.totalPages,
            hasNext: data.hasNext,
        });

        setPending(false);
    };

    useEffect(() => {
        fetchProducts(0);
    }, [pageInfo.size]);

    return (
        <div style={{padding: 16}}>
            <h2>상품 목록</h2>

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
                        <td>{p.name}</td>
                        <td>{Number(p.salePrice).toLocaleString()}</td>
                        <td>{Number(p.costPrice).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div style={{marginTop: 12, display: "flex", gap: 8, alignItems: "center"}}>
                <button
                    onClick={() => fetchProducts(pageInfo.page - 1)}
                    disabled={pending || pageInfo.page === 0}
                >
                    이전
                </button>

                <span>
          {pageInfo.page + 1} / {pageInfo.totalPages}
        </span>

                <button
                    onClick={() => fetchProducts(pageInfo.page + 1)}
                    disabled={pending || !pageInfo.hasNext}
                >
                    다음
                </button>
            </div>
        </div>
    )
}