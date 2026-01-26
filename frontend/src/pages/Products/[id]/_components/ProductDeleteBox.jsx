import { useState } from "react";

export default function ProductDeleteBox({ pending, onDelete }) {
    const [text, setText] = useState("");

    const canDelete = text === "삭제";

    const handleClick = async () => {
        await onDelete();
    };

    return (
        <div style={{ marginTop: 16, border: "1px solid #f2c0c0", padding: 12 }}>
            <h3 style={{ color: "crimson" }}>삭제</h3>

            <div style={{ display: "flex", gap: 8, alignItems: "center" }}>
                <input
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    placeholder='삭제'
                />
                <button type="button" disabled={!canDelete || pending} onClick={handleClick}>
                    삭제 실행
                </button>
            </div>
            <small>정확히 "삭제"를 입력해야 버튼이 활성화됩니다.</small>
        </div>
    );
}
