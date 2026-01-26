export async function fetchProductsApi({page, size, sort}) {
    const res = await fetch(`/products?page=${page}&size=${size}&sort=${encodeURIComponent(sort)}`);
    const json = await res.json();
    if (!res.ok || json?.success === false) {
        throw new Error(json?.message ?? "목록 조회 실패");
    }
    return json.data;
}

export async function createProductApi({name, salePrice, costPrice}) {
    const res = await fetch("/products", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({name, salePrice, costPrice}),
    });
    const json = await res.json();
    if (!res.ok || json?.success === false) {
        throw new Error(json?.message ?? "등록 실패")
    }
    return json.data;
}

export async function updateProductApi(id, {name, salePrice, costPrice}) {
    const res = await fetch(`/products/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({name, salePrice, costPrice}),
    });
    const json = await res.json();
    if (!res.ok || json?.success === false) {
        throw new Error(json?.message ?? "수정 실패");
    }
    return json.data;
}

export async function deleteProductApi(id) {
    const res = await fetch(`/products/${id}`, {method: "DELETE"});
    if (res.status === 204) return null;
    const json = await res.json().catch(() => null);
    if (!res.ok || json?.success === false) {
        throw new Error(json?.message ?? "삭제 실패");
    }
    return json?.data ?? null;
}

export async function fetchProductApi(id) {
    const res = await fetch(`/products/${id}`);
    const json = await res.json();

    if (!res.ok || json?.success === false) {
        throw new Error(json?.message ?? "상품 조회 실패");
    }
    return json.data;
}