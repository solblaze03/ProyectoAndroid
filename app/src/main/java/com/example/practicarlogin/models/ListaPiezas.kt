package com.example.practicarlogin.models

class ListaPiezas {
    companion object {

        val ssdList = listOf(
            storage(
                nombre = "Samsung 970 EVO Plus",
                marca = "Samsung",
                tipo = "SSD",
                tipoDisco = "NVMe",
                tamaño = "2TB",
                Vl = "3500 MB/s",
                VE = "3200 MB/s",
                imagen = "https://images.idgesg.net/images/article/2019/01/samsung-970-evo-plus-primary-100785851-large.jpg",
                precio = 145.99
            ),
            storage(
                nombre = "Crucial P5 Plus",
                marca = "Crucial",
                tipo = "SSD",
                tipoDisco = "NVMe",
                tamaño = "1TB",
                Vl = "3400 MB/s",
                VE = "3000 MB/s",
                imagen = "https://www.crucial.com/content/dam/crucial/ssd-products/p5-plus/images/web/amazon-gallery/en/interoperable-2-en.jpg",
                precio = 75.99
            ),
            storage(
                nombre = "Kingston A2000",
                marca = "Kingston",
                tipo = "SSD",
                tipoDisco = "NVMe",
                tamaño = "1TB",
                Vl = "2200 MB/s",
                VE = "2000 MB/s",
                imagen = "https://images.idgesg.net/images/article/2019/08/kingston-a2000-primary-100808110-large.jpg",
                precio = 67.55
            ),
            storage(
                nombre = "Intel 660p",
                marca = "Intel",
                tipo = "SSD",
                tipoDisco = "NVMe",
                tamaño = "1TB",
                Vl = "1800 MB/s",
                VE = "1350 MB/s",
                imagen = "https://cdn.mos.cms.futurecdn.net/NpQwDrkPApjquTDNQuSUw.jpg",
                precio = 80.55
            ),
            storage(
                nombre = "Samsung 860 EVO",
                marca = "Samsung",
                tipo = "SSD",
                tipoDisco = "SATA",
                tamaño = "1TB",
                Vl = "550 MB/s",
                VE = "520 MB/s",
                imagen = "https://www.techadvisor.com/wp-content/uploads/2022/06/samsung_860_evo_ssd_review.jpg?quality=50&strip=all",
                precio = 65.99
            ),
            storage(
                nombre = "Crucial MX500",
                marca = "Crucial",
                tipo = "SSD",
                tipoDisco = "SATA",
                tamaño = "1TB",
                Vl = "560 MB/s",
                VE = "510 MB/s",
                imagen = "https://th.bing.com/th/id/R.bf92fb5bb18b42bb900ec8c754327938?rik=L3ISLFbEFSutgw&pid=ImgRaw&r=0",
                precio = 55.15
            ),
            storage(
                nombre = "Kingston A400",
                marca = "Kingston",
                tipo = "SSD",
                tipoDisco = "SATA",
                tamaño = "480GB",
                Vl = "500 MB/s",
                VE = "450 MB/s",
                imagen = "https://3.bp.blogspot.com/-y9qc7SY8WkU/WPrvY5dP2vI/AAAAAAAAIRs/sm88j19_xl0FYLqMQnbOIEXTuxs42e5zQCLcB/s1600/Kingston%2BA400%2B%25288%2529.JPG",
                precio = 67.88
            ),
            storage(
                nombre = "Intel 545s",
                marca = "Intel",
                tipo = "SSD",
                tipoDisco = "SATA",
                tamaño = "256GB",
                Vl = "550 MB/s",
                VE = "500 MB/s",
                imagen = "https://c1.neweggimages.com/ProductImageCompressAll1280/20-167-429-V05.jpg",
                precio = 21.99
            ),
            storage(
                nombre = "Samsung 970 EVO",
                marca = "Samsung",
                tipo = "SSD",
                tipoDisco = "NVMe",
                tamaño = "500GB",
                Vl = "3500 MB/s",
                VE = "3200 MB/s",
                imagen = "https://th.bing.com/th/id/OIP._3xy_z6jjoIzCvcAFNuLfgAAAA?rs=1&pid=ImgDetMain",
                precio = 39.99
            ),
            storage(
                nombre = "Crucial P5",
                marca = "Crucial",
                tipo = "SSD",
                tipoDisco = "NVMe",
                tamaño = "512GB",
                Vl = "3400 MB/s",
                VE = "3000 MB/s",
                imagen = "https://pics.computerbase.de/9/8/4/4/7-f31ad8e09a08b1d7/9-1080.9960c0e9.jpg",
                precio = 45.99
            )
        )


        val graphicList = listOf(
            Graphic(
                nombre = "GeForce RTX 3080",
                marca = "NVIDIA",
                consumo = 320.0,
                vram = "10GB",
                imagen = "https://m.media-amazon.com/images/I/81SzNmM27EL.jpg",
                tipoMemoria = "GDDR6X",
                rtx = true,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 699.99,
                altura = 69.0,
                longitud = 170.0,
                ensamblador = "ASUS",
                rendimientoMinimo = 53.2 // Procesador mínimo: 6 núcleos, 12 hilos, 2.4 GHz -> (6 + 12) * 2.4
            ),
            Graphic(
                nombre = "Radeon RX 6800 XT",
                marca = "AMD",
                consumo = 300.0,
                vram = "16GB",
                imagen = "https://c1.neweggimages.com/ProductImageCompressAll1280/14-126-477-V08.jpg",
                tipoMemoria = "GDDR6",
                rtx = true,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 649.99,
                altura = 120.0,
                longitud = 267.0,
                ensamblador = "ASUS",
                rendimientoMinimo = 49.8 // Procesador mínimo: 6 núcleos, 12 hilos, 2.6 GHz -> (6 + 12) * 2.6
            ),
            Graphic(
                nombre = "GeForce GTX 1660 Super",
                marca = "NVIDIA",
                consumo = 125.0,
                vram = "6GB",
                imagen = "https://os-jo.com/image/cache/catalog/products/Graphics-cards/GTX-1660-Super-VENTUS-XS-OC/619Ui9f--pL._AC_SL1000_-1200x1200.jpg",
                tipoMemoria = "GDDR6",
                rtx = false,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 229.99,
                altura = 115.0,
                longitud = 204.0,
                ensamblador = "MSI",
                rendimientoMinimo = 36.0 // Procesador mínimo: 4 núcleos, 8 hilos, 2.5 GHz -> (4 + 8) * 2.5
            ),
            Graphic(
                nombre = "Radeon RX 5700 XT",
                marca = "AMD",
                consumo = 225.0,
                vram = "8GB",
                imagen = "https://th.bing.com/th/id/R.bd52c26ebb26ee9eef320ba8dd74e62c?rik=zhjr9GiZJHcH%2fA&pid=ImgRaw&r=0",
                tipoMemoria = "GDDR6",
                rtx = false,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 399.99,
                altura = 114.0,
                longitud = 267.0,
                ensamblador = "Gigabyte",
                rendimientoMinimo = 40.0 // Procesador mínimo: 6 núcleos, 6 hilos, 2.5 GHz -> (6 + 6) * 2.5
            ),
            Graphic(
                nombre = "GeForce RTX 3090",
                marca = "NVIDIA",
                consumo = 350.0,
                vram = "24GB",
                imagen = "https://c1.neweggimages.com/ProductImageCompressAll1280/14-126-456-V12.jpg",
                tipoMemoria = "GDDR6X",
                rtx = true,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 1499.99,
                altura = 140.1,
                longitud = 313.0,
                ensamblador = "ASUS",
                rendimientoMinimo = 48.0 // Procesador mínimo: 8 núcleos, 8 hilos, 3.0 GHz -> (8 + 8) * 3.0
            ),
            Graphic(
                nombre = "Radeon RX 6700 XT",
                marca = "AMD",
                consumo = 230.0,
                vram = "12GB",
                imagen = "https://www.ask-corp.jp/products/images/sapphire/sapphire-radeon-rx-6700-xt-12g-gddr6_01.jpg",
                tipoMemoria = "GDDR6",
                rtx = false,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 479.99,
                altura = 125.0,
                longitud = 267.0,
                ensamblador = "Sapphire",
                rendimientoMinimo = 41.6 // Procesador mínimo: 6 núcleos, 10 hilos, 2.6 GHz -> (6 + 10) * 2.6
            ),
            Graphic(
                nombre = "GeForce GTX 1080 Ti",
                marca = "NVIDIA",
                consumo = 250.0,
                vram = "11GB",
                imagen = "https://images.evga.com/products/gallery/11G-P4-6390-KR_XL_1.jpg",
                tipoMemoria = "GDDR5X",
                rtx = false,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 799.99,
                altura = 111.15,
                longitud = 267.0,
                ensamblador = "EVGA",
                rendimientoMinimo = 30.0 // Procesador mínimo: 4 núcleos, 8 hilos, 2.5 GHz -> (4 + 8) * 2.5
            ),
            Graphic(
                nombre = "Radeon RX 5600 XT",
                marca = "AMD",
                consumo = 150.0,
                vram = "6GB",
                imagen = "https://img.pccomponentes.com/articles/30/308994/1157-powercolor-radeon-rx-5600-xt-itx-6gb-gddr6.jpg",
                tipoMemoria = "GDDR6",
                rtx = false,
                conectoresPantalla = "HDMI, DisplayPort",
                precio = 279.99,
                altura = 110.0,
                longitud = 250.0,
                ensamblador = "PowerColor",
                rendimientoMinimo = 33.6 // Procesador mínimo: 4 núcleos, 8 hilos, 2.8 GHz -> (4 + 8) * 2.8

            ),
            Graphic(
                nombre = "NVIDIA GeForce GT 710",
                marca = "NVIDIA",
                consumo = 19.0,
                vram = "2GB",
                imagen = "https://dlcdnwebimgs.asus.com/gain/72387687-0437-45b6-83e1-2f8dfb34efba/w692",
                tipoMemoria = "DDR3",
                rtx = false,
                conectoresPantalla = "HDMI, DVI, D-Sub",
                precio = 40.0,
                altura = 110.0,
                longitud = 250.0,
                ensamblador = "PowerColor",
                rendimientoMinimo = 21.0 // Procesador mínimo: 4 núcleos, 8 hilos, 2.8 GHz -> (4 + 8) * 2.8

            )

        )

        val cajaList = listOf(
            Caja(
                nombre = "NZXT H510",
                factorForma = "ATX",
                marca = "NZXT",
                ventiladores = 3,
                imagen = "https://www.bhphotovideo.com/images/images1000x1000/nzxt_ca_h510e_b1_h510_elite_mid_tower_atx_1507551.jpg",
                peso = "7.5 kg",
                rgb = true,
                longitudMaximaGPU = 381.0,
                alturaGPU = 185.0,
                longitudChasis = 428.0,
                alturaChasis = 480.0,
                precio = 109.99
            ),
            Caja(
                nombre = "Cooler Master MasterBox Q300L",
                factorForma = "Micro-ATX",
                marca = "Cooler Master",
                ventiladores = 2,
                imagen = "https://www.falconcomputers.co.uk/media/product/76514/0/0/cooler-master-1-masterbox-q300l-computer-case-magnetic-patterned-dust-filters-modular.jpg?",
                peso = "5.2 kg",
                rgb = false,
                longitudMaximaGPU = 344.0,
                alturaGPU = 160.0,
                longitudChasis = 421.0,
                alturaChasis = 320.0,
                precio = 79.99
            ),
            Caja(
                nombre = "Fractal Design Node 202",
                factorForma = "Mini-ITX",
                marca = "Fractal Design",
                ventiladores = 2,
                imagen = "https://www.fractal-design.com/app/uploads/2022/03/Node-202-05-Standing-Front-Left-1.jpg",
                peso = "4.0 kg",
                rgb = false,
                longitudMaximaGPU = 310.0,
                alturaGPU = 145.0,
                longitudChasis = 389.0,
                alturaChasis = 295.0,
                precio = 89.99
            ),
            Caja(
                nombre = "Corsair 7000D Airflow",
                factorForma = "ATX",
                marca = "Corsair",
                ventiladores = 5,
                imagen = "https://dynaquestpc.com/cdn/shop/products/CC-9011218-WW.jpg?v=1632259397&width=1214",
                peso = "10.5 kg",
                rgb = true,
                longitudMaximaGPU = 450.0,
                alturaGPU = 200.0,
                longitudChasis = 550.0,
                alturaChasis = 600.0,
                precio = 159.99
            ),
            Caja(
                nombre = "Phanteks Eclipse P400A",
                factorForma = "ATX",
                marca = "Phanteks",
                ventiladores = 3,
                imagen = "https://files.pccasegear.com/images/1603068871-PH-EC400ATG_DBK01_SYS02-thb.jpg",
                peso = "6.8 kg",
                rgb = false,
                longitudMaximaGPU = 360.0,
                alturaGPU = 170.0,
                longitudChasis = 450.0,
                alturaChasis = 470.0,
                precio = 99.99
            )
        )


        val listaFuentes = listOf(
            fuente(
                nombre = "Corsair RM850x",
                marca = "Corsair",
                potencia = "850W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 8-pin EPS, 6x PCIe",
                precio = 129.99,
                imagen = "https://www.e-weekly.co.uk/images/johnmac/corsair/csr-rm850x/images/cp-9020180-uk-rm850x_psu_01.jpg?lce",
                modularidad = "Modular"
            ),
            fuente(
                nombre = "EVGA SuperNOVA 750 G5",
                marca = "EVGA",
                potencia = "750W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 2x EPS, 4x PCIe",
                precio = 119.99,
                imagen = "https://images-na.ssl-images-amazon.com/images/I/71HHpGHjI%2BL._AC_SL1500_.jpg",
                modularidad = "Modular"
            ),
            fuente(
                nombre = "Seasonic Focus GX-650",
                marca = "Seasonic",
                potencia = "650W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 1x EPS, 4x PCIe",
                precio = 104.99,
                imagen = "https://microless.com/cdn/products/cfc4882276fde900bc872211f2a051b8-hi.jpg",
                modularidad = "Semimodular"
            ),
            fuente(
                nombre = "Be Quiet! Straight Power 11",
                marca = "Be Quiet!",
                potencia = "850W",
                certificacion = "80 PLUS Platinum",
                conectores = "24-pin ATX, 2x EPS, 6x PCIe",
                precio = 159.99,
                imagen = "https://www.bequiet.com/volumes/pim/powersupply/straightpower/straightpower11/Straight_Power_11_rgb.jpg",
                modularidad = "Modular"
            ),
            fuente(
                nombre = "Thermaltake Smart BX1",
                marca = "Thermaltake",
                potencia = "550W",
                certificacion = "80 PLUS Bronze",
                conectores = "24-pin ATX, 1x EPS, 2x PCIe",
                precio = 59.99,
                imagen = "https://www.falconcomputers.co.uk/media/product/82341/0/0/thermaltake-650w-smart-bx1-rgb-80-bronze-psu---48-amp-single-rail.jpg.jpg",
                modularidad = "No modular"
            ),
            fuente(
                nombre = "Cooler Master V850 SFX",
                marca = "Cooler Master",
                potencia = "850W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 1x EPS, 4x PCIe",
                precio = 169.99,
                imagen = "https://computerarenakh.com/image/catalog/SEA%20DARA/4.PSU/image%20(2).jpg",
                modularidad = "Modular"
            ),
            fuente(
                nombre = "Gigabyte P750GM",
                marca = "Gigabyte",
                potencia = "750W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 2x EPS, 4x PCIe",
                precio = 109.99,
                imagen = "https://media.ldlc.com/r1600/ld/products/00/05/73/80/LD0005738044_1.jpg",
                modularidad = "Semimodular"
            ),
            fuente(
                nombre = "NZXT C650",
                marca = "NZXT",
                potencia = "650W",
                certificacion = "80 PLUS Bronze",
                conectores = "24-pin ATX, 1x EPS, 3x PCIe",
                precio = 99.99,
                imagen = "https://www.falconcomputers.co.uk/media/product/88093/0/0/nzxt-c650-650w-80-gold-fully-modular-power-supply.jpg.jpg",
                modularidad = "Modular"
            ),
            fuente(
                nombre = "ASUS ROG Strix 850W",
                marca = "ASUS",
                potencia = "850W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 2x EPS, 6x PCIe",
                precio = 179.99,
                imagen = "https://media.ldlc.com/bo/images/fiches/Alimentation/Asus/rog_strix/asus_rog_strix_850.jpg",
                modularidad = "Modular"
            ),
            fuente(
                nombre = "Antec Earthwatts Gold Pro",
                marca = "Antec",
                potencia = "750W",
                certificacion = "80 PLUS Gold",
                conectores = "24-pin ATX, 1x EPS, 4x PCIe",
                precio = 89.99,
                imagen = "https://c1.neweggimages.com/ProductImageCompressAll1280/17-371-105_R11.jpg",
                modularidad = "Semimodular"
            )
        )


    }
}