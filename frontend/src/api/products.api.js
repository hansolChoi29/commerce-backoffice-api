export async function fetchProductsApi({ page, size, sort }) {
    const res = await fetch(`/products?page=${page}&size=${size}&sort=${encodeURIComponent(sort)}`);
    const json = await res.json();
    if (!res.ok || json?.success === false) {
        throw new Error(json?.message ?? "목록 조회 실패");
    }
    return json.data;
}

export async function createProductApi(){

}

export async function updateProductApi(){

}

export async function deleteProductApi(){

}