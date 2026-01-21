import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css";

export default function LoginPage() {
    const navigate = useNavigate();

    const [form, setForm] = useState({ email: "", password: "" });
    const [status, setStatus] = useState({ loading: false, error: "" });

    const onChange = (e) => {
        setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
        if (status.error) setStatus((s) => ({ ...s, error: "" }));
    };

    const validate = () => {
        if (!form.email.trim()) return "이메일을 입력해 주세요.";
        if (!form.password.trim()) return "비밀번호를 입력해 주세요.";
        // 아주 간단한 이메일 형태 체크(과한 정규식 X)
        if (!form.email.includes("@")) return "이메일 형식이 올바르지 않습니다.";
        return "";
    };

    const onSubmit = async (e) => {
        e.preventDefault();

        const msg = validate();
        if (msg) {
            setStatus({ loading: false, error: msg });
            return;
        }

        try {
            setStatus({ loading: true, error: "" });

            // TODO: 여기서 백엔드 로그인 API 호출하면 됨
            // const res = await fetch("/api/auth/login", {...})
            // const data = await res.json()
            // ✅ 임시: 토큰 저장(ProtectedRoute 통과용)
            localStorage.setItem("accessToken", "dev-token");
            // 로그인 성공 후 이동
            navigate("/admin/products", { replace: true });
            ////////////////

        } catch (err) {
            setStatus({ loading: false, error: "로그인에 실패했습니다. 다시 시도해 주세요." });
        } finally {
            setStatus((s) => ({ ...s, loading: false }));
        }
    };

    return (
        <section className="login">
            <div className="login__card" role="region" aria-label="로그인">
                <header className="login__header">
                    <div className="login__badge">Ledger Admin</div>
                    <h1 className="login__title">로그인</h1>
                    <p className="login__subtitle">관리자 계정으로 접속해 주세요.</p>
                </header>

                <form className="login__form" onSubmit={onSubmit}>
                    <div className="login__field">
                        <label className="login__label" htmlFor="email">이메일</label>
                        <input
                            id="email"
                            name="email"
                            type="email"
                            autoComplete="email"
                            className="login__input"
                            placeholder="admin@ledger.com"
                            value={form.email}
                            onChange={onChange}
                            disabled={status.loading}
                        />
                    </div>

                    <div className="login__field">
                        <label className="login__label" htmlFor="password">비밀번호</label>
                        <input
                            id="password"
                            name="password"
                            type="password"
                            autoComplete="current-password"
                            className="login__input"
                            placeholder="••••••••"
                            value={form.password}
                            onChange={onChange}
                            disabled={status.loading}
                        />
                    </div>

                    {status.error && (
                        <div className="login__error" role="alert">
                            {status.error}
                        </div>
                    )}

                    <button className="login__button" type="submit" disabled={status.loading}>
                        {status.loading ? "로그인 중..." : "로그인"}
                    </button>

                    <div className="login__footer">
                        <span className="login__hint">개발용 임시 로그인: 아무 값이나 넣고 로그인 가능</span>
                    </div>
                </form>
            </div>
        </section>
    );
}
