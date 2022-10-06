import { Route, Routes } from "react-router-dom";
import { DefaultLayout } from "./layouts/DefaultLayout";
import { Home } from "./pages/Home";
import { AccountsSection } from "./pages/AccountsSection";
import { DeleteSection } from "./pages/DeleteSection";
import { CreateSection } from "./pages/CreateSection";
import { Navigate } from "react-router-dom";

export function Router() {
  return (
    <Routes>
      <Route path="/" element={<DefaultLayout />}>
          <Route path="/" element={<Navigate to={"/home"} />} />
          <Route path="/home" element={<Home />} />
          <Route path="/accounts" element={<AccountsSection />} />
          <Route path="/delete" element={<DeleteSection />} />
          <Route path="/create" element={<CreateSection />} />

      </Route>
    </Routes>
  );
}
