package com.duhdoesk.supertrunfoclone.datasource

import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Carta

class Datasource {

    companion object {

        fun getDeck(index: Int): Baralho {

            val deckCarrosDeLuxo = Baralho(
                "Carros de Luxo", "Cilindradas", "Velocidade Máxima", "Potência",
                "Comprimento", "ccm", "km/h", "cv", "m",
                cartas = mutableListOf(
                    Carta("1A", "https://www.supercars.net/blog/wp-content/uploads/2016/04/2001_Maserati_3200GTAssettoCorsa1.jpg", "Maserati 'Asseto Corsa'", 3217, 280, 369, 4.51),
                    Carta("2A", "https://cdn.classic-trader.com/I/images/1920_1920_inset/vehicle_ad_standard_image_9fb111198f0ea1df4ee7eb7f2cf0ac56.jpg", "Rolls-Royce Park Ward", 5379, 225, 326, 5.65),
                    Carta("3A", "https://besthqwallpapers.com/Uploads/21-12-2019/116928/thumb2-lexus-rx-300-road-2003-cars-suvs-xu10.jpg", "Lexus RX 300", 2995, 180, 201, 4.58),
                    Carta("4A", "https://1.bp.blogspot.com/-iWJMT7tCJw0/UEjDvXTZamI/AAAAAAABHOY/PQBF3qfArJU/s1600/001.jpg", "Bugatti EB 16.4 Veyron", 7993, 404, 1001, 4.38),
                    Carta("5A", "https://www.autolivraria.com.br/carros/vw/d1-2002-3g.jpg", "VW D1", 6000, 250, 450, 4.95),
                    Carta("6A", "https://hips.hearstapps.com/autoweek/assets/s3fs-public/131019835.jpg?crop=1xw:0.8xh;center,top&resize=1200:*", "Binz XL", 4266, 230, 279, 5.79),
                    Carta("7A", "https://www.ultimatecarpage.com/images/car/2100/Pagani-Zonda-C12-S-Monza-15297.jpg", "Pagani Zonda C12 S", 7010, 350, 550, 4.40),
                    Carta("8A", "https://file.kelleybluebookimages.com/kbb/base/house/2001/2001-Lexus-LS-FrontSide_LELS4011_505x375.jpg", "Lexus LS 430", 4293, 250, 282, 5.00),
                    Carta("1B", "https://i.gaw.to/content/photos/10/71/107159_2012_Jaguar_XK.jpg", "Jaguar XKR", 3996, 250, 363, 4.76),
                    Carta("2B", "https://media.carsandbids.com/cdn-cgi/image/width=2080,quality=70/da4b9237bacccdf19c0760cab7aec4a8359010b0/photos/DSC05814.7SGxpNAMw.jpg?t=160503654196", "Mercedes S 63 AMG", 6258, 250, 444, 5.16),
                    Carta("3B", "https://img.favcars.com/mercedes-benz/m-klasse/wallpapers_mercedes-benz_m-klasse_2000_3_b.jpg", "Mercedes ML 55 AMG", 5439, 235, 347, 4.64),
                    Carta("4B", "https://www.carpixel.net/w/f2ab72b2b24abc9d0a4aabffc1fdb880/ferrari-550-maranello-wallpaper-hd-70960.jpg", "Ferrari 550 Maranello", 5474, 320, 485, 4.55),
                    Carta("5B", "https://www.supercars.net/blog/wp-content/uploads/2016/01/Alfa-Romeo-Scighera-Concept-Car.jpg", "ItalDesign Scighera", 3000, 310, 400, 4.32),
                    Carta("6B", "https://www.rrsilverspirit.com/Hooper/Emperor/best/Spirit%20Emperor%201989%20KCH26441%203.jpg", "Hooper Rolls-Royce Emperor", 4293, 250, 282, 5.00),
                    Carta("7B", "https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/9810101251161921600x1060_0.jpg?itok=VXhbdeE7", "Morgan Aero 8", 4398, 258, 286, 4.09),
                    Carta("8B", "https://heycar.com.br/images/2017/Abril/BMW-745-2003.jpg", "BMW 745i", 4398, 250, 333, 5.00),
                    Carta("1C", "https://www.planetcarsz.com/img/carros/2021/09/porsche-911-gt2-2008-021-20210923220453-1600x1200.jpg", "Porsche 911 GT2", 3600, 315, 462, 4.45),
                    Carta("2C", "https://images.squarespace-cdn.com/content/v1/51722267e4b0532e10d4c7aa/1375394041025-V4ATSIHOR5SYBOEQLZ7D/daimler.jpg?format=1000w", "Daimier Super V8", 3996, 250, 363, 5.15),
                    Carta("3C", "https://motos-motor.com.br/c/wp-content/uploads/precos-tabela-land-rover-discovery3-hse-44-v8-4x4-299cv-aut.jpg", "Range Rover", 4553, 196, 218, 4.71),
                    Carta("4C", "https://cdn.motor1.com/images/mgl/nvbOk/s1/casil-motors-sp-110-edonis-fenice.jpg", "B. Engineering Edonis", 3760, 365, 680, 4.35),
                    Carta("5C", "https://www.planetcarsz.com/assets/uploads/images/VEICULOS/C/CADILLAC/2000_CADILLAC_IMAJ//CADILLAC_IMAJ_2000_01.jpg", "Cadillac Imaj", 4200, 300, 431, 5.10),
                    Carta("6C", "https://i.pinimg.com/564x/21/94/51/21945142e612af348025dacb5439e2f0.jpg", "American Custom Lincoln", 5409, 200, 380, 6.40),
                    Carta("7C", "https://www.supercars.net/blog/wp-content/uploads/2016/04/615393.jpg", "Mega Monte Carlo", 5991, 300, 395, 4.45),
                    Carta("8C", "https://i.pinimg.com/originals/e9/36/91/e936914e77f238595be591bfa52189c4.jpg", "Cadillac Deville DTS", 4565, 240, 305, 5.26),
                    Carta("1D", "https://www.encontracarros.com.br/upload/aston-martin/aston-martin-vanquish-1280-01.jpg", "Aston Martin Vanquish", 5935, 306, 460, 4.67, true),
                    Carta("2D", "https://www.supersprint.com/public/img/1.-359392.jpg", "Bentley Continental T", 6750, 270, 426, 5.22),
                    Carta("3D", "https://file.kelleybluebookimages.com/kbb/base/house/2000/2000-BMW-X5-FrontSide_BTX5-001_505x375.jpg", "BMW X5", 4398, 207, 286, 4.67),
                    Carta("4D", "https://i.pinimg.com/736x/ef/94/85/ef948560d1b9b145c0e5892de8b79f90--lamborghini-diablo-view-photos.jpg", "Lamborghini Diablo", 5992, 330, 550, 4.47),
                    Carta("5D", "https://www.planetcarsz.com/assets/uploads/2015/01/CHRYSLER%20CHRONOS%202000%2002.jpg", "Chrysler Chronos", 5980, 280, 350, 5.22),
                    Carta("6D", "https://carsalesbase.com/wp-content/uploads/2015/10/Maybach-U.S-Sales-Figures.jpg", "Maybach Langversion", 5600, 250, 470, 6.10),
                    Carta("7D", "https://www.below-the-radar.com/wp-content/uploads/2020/11/Lotec-Sirius-11-1500x1000.jpg?v=1606516681", "Lotec Sirius", 5987, 400, 1200, 4.12),
                    Carta("8D", "https://bestcarmagz.net/sites/default/files/63891387493760003_large.jpg", "Audi S8", 4172, 250, 360, 5.03)
                )
            )

            val deck: Baralho = when (index) {
                0 -> deckCarrosDeLuxo
                else -> deckCarrosDeLuxo
            }

            deck.cartas = deck.cartas.shuffled()
            return deck
        }

        fun splitCards(baralho: Baralho, player: String): List<Carta>? {

            val totalCartas: Int = baralho.cartas.size

            val deck = when (player) {
                "me" -> baralho.cartas.subList(0, (totalCartas + 1) / 2)
                "opp" -> baralho.cartas.subList((totalCartas + 1) / 2, totalCartas)
                else -> null
            }

            return deck
        }

        fun cardBattle(myCardValue: Int, oppCardValue: Int): String {
            return if (myCardValue > oppCardValue) "Player" else "CPU"
        }

        fun cardBattle(myCardValue: Double, oppCardValue: Double): String {
            return if (myCardValue > oppCardValue) "Player" else "CPU"
        }

        fun superTrunfo(cardId: String): Boolean {
            val pattern = Regex("A")
            return pattern.containsMatchIn(cardId)
        }
    }
}