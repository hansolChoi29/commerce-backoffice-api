import {useEffect, useState} from "react";

export default function ProductEditForm({item, pending, onSave}) {
    const [name, setName] = useState("");
    const [salePrice, setSalePrice] = useState("");
    const [costPrice, setCostPrice] = useState("");
    const [status, setStatus] = useState("ACTIVE");

    useEffect(() => {
        if (!item) return;

        setName(item.name ?? "");
        setSalePrice(String(item.salePrice ?? ""));
        setCostPrice(String(item.costPrice ?? ""));
        setStatus(item.status ?? "ACTIVE");
    }, [item]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        await onSave({
            name: name.trim(),
            salePrice: Number(salePrice),
            costPrice: Number(costPrice),
            status,
        });
    };

    return (
        <form onSubmit={handleSubmit} style={{marginTop: 16, border: "1px solid #ddd", padding: 12}}>
            <h3>수정</h3>

            <div style={{display: "grid", gap: 8, maxWidth: 360}}>
                <label>
                    상품명
                    <input value={name} onChange={(e) => setName(e.target.value)}/>
                </label>

                <label>
                    판매가
                    <input value={salePrice} onChange={(e) => setSalePrice(e.target.value)}/>
                </label>

                <label>
                    원가
                    <input value={costPrice} onChange={(e) => setCostPrice(e.target.value)}/>
                </label>

                <label>
                    상태
                    <select value={status} onChange={(e) => setStatus(e.target.value)}>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="INACTIVE">INACTIVE</option>
                    </select>
                </label>

                <button type="submit" disabled={pending}>
                    저장
                </button>
            </div>
        </form>
    );
}
