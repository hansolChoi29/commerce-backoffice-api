import { useState } from "react";

export default function ProductCreateForm({ pending, onCreate }) {
    const [name, setName] = useState("");
    const [salePrice, setSalePrice] = useState("");
    const [costPrice, setCostPrice] = useState("");

    const submit = (e) => {
        e.preventDefault();
        onCreate({
            name,
            salePrice: Number(salePrice),
            costPrice: Number(costPrice),
        });
    };

    return (
        <form onSubmit={submit} style={{ display: "flex", gap: 8, marginBottom: 16, flexWrap: "wrap" }}>
            <input placeholder="상품명" value={name} onChange={(e) => setName(e.target.value)} />
            <input placeholder="판매가" type="number" value={salePrice} onChange={(e) => setSalePrice(e.target.value)} />
            <input placeholder="원가" type="number" value={costPrice} onChange={(e) => setCostPrice(e.target.value)} />
            <button type="submit" disabled={pending || !name || salePrice === "" || costPrice === ""}>
                등록
            </button>
        </form>
    );
}
