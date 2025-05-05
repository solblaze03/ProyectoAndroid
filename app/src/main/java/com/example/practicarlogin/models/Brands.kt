package com.example.practicarlogin.models

import androidx.annotation.ColorRes
import com.example.practicarlogin.R


enum class Brands(val brand: String, @ColorRes val colorResId: Int) {
    // CPUs
    AMD("AMD", R.color.brand_amd),
    Intel("Intel", R.color.brand_intel),

    // Placas base y GPUs
    ASUS("ASUS", R.color.brand_asus),
    MSI("MSI", R.color.brand_msi),
    Gigabyte("Gigabyte", R.color.brand_gigabyte),
    EVGA("EVGA", R.color.brand_evga),
    ASRock("ASRock", R.color.brand_asrock),
    BIOSTAR("Biostar", R.color.brand_biostar),
    Zotac("Zotac", R.color.brand_zotac),
    Sapphire("Sapphire", R.color.brand_sapphire),
    PowerColor("PowerColor", R.color.brand_powercolor),
    XFX("XFX", R.color.brand_xfx),
    Palit("Palit", R.color.brand_palit),
    Inno3D("Inno3D", R.color.brand_inno3d),

    // RAM
    Corsair("Corsair", R.color.brand_corsair),
    GSkill("G.Skill", R.color.brand_gskill),
    Kingston("Kingston", R.color.brand_kingston),
    HyperX("HyperX", R.color.brand_hyperx),
    Crucial("Crucial", R.color.brand_crucial),
    TeamGroup("TeamGroup", R.color.brand_teamgroup),
    Adata("ADATA", R.color.brand_adata),
    Patriot("Patriot", R.color.brand_patriot),
    GeIL("GeIL", R.color.brand_geil),

    // Almacenamiento
    Samsung("Samsung", R.color.brand_samsung),
    WD("Western Digital", R.color.brand_wd),
    Seagate("Seagate", R.color.brand_seagate),
    Toshiba("Toshiba", R.color.brand_toshiba),
    Sandisk("SanDisk", R.color.brand_sandisk),
    IntelStorage("Intel Storage", R.color.brand_intel_storage),
    SKHynix("SK Hynix", R.color.brand_skhynix),
    PNY("PNY", R.color.brand_pny),

    // Fuentes, cajas y refrigeración
    Cooler_Master("Cooler Master", R.color.brand_cooler_master),
    NZXT("NZXT", R.color.brand_nzxt),
    BeQuiet("be quiet!", R.color.brand_bequiet),
    Thermaltake("Thermaltake", R.color.brand_thermaltake),
    Antec("Antec", R.color.brand_antec),
    Fractal_Design("Fractal Design", R.color.brand_fractal_design),
    LianLi("Lian Li", R.color.brand_lianli),
    Silverstone("SilverStone", R.color.brand_silverstone),
    Seasonic("Seasonic", R.color.brand_seasonic),
    DeepCool("DeepCool", R.color.brand_deepcool),
    Noctua("Noctua", R.color.brand_noctua),
    Arctic("Arctic", R.color.brand_arctic),
    EKWB("EKWB", R.color.brand_ekwb),

    // Periféricos
    Logitech("Logitech", R.color.brand_logitech),
    Razer("Razer", R.color.brand_razer),
    SteelSeries("SteelSeries", R.color.brand_steelseries),
    CorsairGaming("Corsair Gaming", R.color.brand_corsair),
    HyperXGaming("HyperX Gaming", R.color.brand_hyperx),
    AsusROG("ASUS ROG", R.color.brand_asusrog),
    Redragon("Redragon", R.color.brand_redragon),
    Trust("Trust", R.color.brand_trust),
    Cougar("Cougar", R.color.brand_cougar)
}
