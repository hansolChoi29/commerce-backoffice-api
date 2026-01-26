import { useEffect, useState } from "react";

export default function ProductEditForm({ item, pending, onSave, onCancel }) {
    const [name, setName] = useState("");
    const [salePrice, setSalePrice] = useState("");
    const [costPrice, setCostPrice] = useState("");
    const [status, setStatus] = useState("");

    useEffect(() => {
        if (!item) return;
        setName(item.name ?? "");
        setSalePrice(String(item.salePrice ?? ""));
        setCostPrice(String(item.costPrice ?? ""));
        setStatus(String(item.status ?? ""));
    }, [item]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        await onSave({
            name: name.trim(),
            salePrice: Number(salePrice),
            costPrice: Number(costPrice),
            status: status.trim(),
        });
    };

    return (
        <form onSubmit={handleSubmit} style={{ marginTop: 16, border: "1px solid #eee", padding: 12, borderRadius: 12 }}>
            <div style={{ display: "grid", gap: 10 }}>
                <label>
                    상품명
                    <input value={name} onChange={(e) => setName(e.target.value)} />
                </label>

                <label>
                    판매가
                    <input value={salePrice} onChange={(e) => setSalePrice(e.target.value)} />
                </label>

                <label>
                    원가
                    <input value={costPrice} onChange={(e) => setCostPrice(e.target.value)} />
                </label>

                <label>
                    상태
                    <input value={status} onChange={(e) => setStatus(e.target.value)} />
                </label>

                <div style={{ display: "flex", gap: 8 }}>
                    <button type="submit" disabled={pending}>저장</button>
                    <button type="button" onClick={onCancel} disabled={pending}>취소</button>
                </div>
            </div>
        </form>
    );
}
