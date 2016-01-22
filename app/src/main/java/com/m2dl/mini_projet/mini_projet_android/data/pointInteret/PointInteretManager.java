package com.m2dl.mini_projet.mini_projet_android.data.pointInteret;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointInteretManager {

    private Map<PointInteretType, List<MarkerOptions>> mapPointInteret;

    public PointInteretManager() {
        init();
    }

    public Map<PointInteretType, List<MarkerOptions>> getMapPointInteret() {
        return mapPointInteret;
    }

    private void init() {
        mapPointInteret = new HashMap<>();

        List<MarkerOptions> interetCarton = new ArrayList<>();
        mapPointInteret.put(PointInteretType.CARTON, interetCarton);

        List<MarkerOptions> interetPapier = new ArrayList<>();
        mapPointInteret.put(PointInteretType.PAPIER, interetPapier);

        List<MarkerOptions> interetPiles = new ArrayList<>();
        mapPointInteret.put(PointInteretType.PILES, interetPiles);

        List<MarkerOptions> interetTextiles = new ArrayList<>();
        mapPointInteret.put(PointInteretType.TEXTILES, interetTextiles);

        List<MarkerOptions> interetVerre = new ArrayList<>();
        mapPointInteret.put(PointInteretType.VERRE, interetVerre);

        // Carton
        List<LatLng> listCarton = new ArrayList<>();

        listCarton.add(new LatLng(43.55773,1.46920));
        listCarton.add(new LatLng(43.55780,1.46934));
        listCarton.add(new LatLng(43.55745,1.46969));
        listCarton.add(new LatLng(43.55738,1.46954));
        listCarton.add(new LatLng(43.55992,1.47172));
        listCarton.add(new LatLng(43.56000,1.47186));
        listCarton.add(new LatLng(43.55948,1.47236));
        listCarton.add(new LatLng(43.55942,1.47220));
        listCarton.add(new LatLng(43.5622,1.46751));
        listCarton.add(new LatLng(43.56231,1.4677));
        listCarton.add(new LatLng(43.56206,1.46795));
        listCarton.add(new LatLng(43.56197,1.46772));
        listCarton.add(new LatLng(43.56449,1.4656));
        listCarton.add(new LatLng(43.56455,1.46576));
        listCarton.add(new LatLng(43.56406,1.46625));
        listCarton.add(new LatLng(43.56398,1.46609));
        listCarton.add(new LatLng(43.56577,1.46736));
        listCarton.add(new LatLng(43.56593,1.46769));
        listCarton.add(new LatLng(43.56577,1.46785));
        listCarton.add(new LatLng(43.5656,1.46753));
        listCarton.add(new LatLng(43.56665,1.4695));
        listCarton.add(new LatLng(43.56674,1.46968));
        listCarton.add(new LatLng(43.56657,1.46985));
        listCarton.add(new LatLng(43.5665,1.46965));

        for (LatLng position : listCarton) {
            interetCarton.add(new MarkerOptions()
                    .position(position)
                    .title(PointInteretType.CARTON.toString())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(PointInteretType.CARTON.getColor())));
        }

        // Piles
        List<LatLng> listPiles = new ArrayList<>();

        listPiles.add(new LatLng(43.56362,1.46487));
        listPiles.add(new LatLng(43.56381,1.46537));
        listPiles.add(new LatLng(43.56354,1.4657));
        listPiles.add(new LatLng(43.5633,1.46522));
        listPiles.add(new LatLng(43.56456,1.46589));
        listPiles.add(new LatLng(43.56472,1.46617));
        listPiles.add(new LatLng(43.56458,1.46629));
        listPiles.add(new LatLng(43.56446,1.46602));

        for (LatLng position : listPiles) {
            interetPiles.add(new MarkerOptions()
                    .position(position)
                    .title(PointInteretType.PILES.toString())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(PointInteretType.PILES.getColor())));
        }

        // Textile
        List<LatLng> listTextile = new ArrayList<>();

        listTextile.add(new LatLng(43.55505,1.46811));
        listTextile.add(new LatLng(43.56305,1.45935));

        for (LatLng position : listTextile) {
            interetTextiles.add(new MarkerOptions()
                    .position(position)
                    .title(PointInteretType.TEXTILES.toString())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(PointInteretType.TEXTILES.getColor())));
        }

        // Papier
        List<LatLng> listPapier = new ArrayList<>();

        listPapier.add(new LatLng(43.56758,1.46950));
        listPapier.add(new LatLng(43.56773,1.46989));
        listPapier.add(new LatLng(43.56733,1.47026));
        listPapier.add(new LatLng(43.56716,1.46991));
        listPapier.add(new LatLng(43.56666,1.46950));
        listPapier.add(new LatLng(43.56674,1.46967));
        listPapier.add(new LatLng(43.56667,1.46975));
        listPapier.add(new LatLng(43.56657,1.46958));
        listPapier.add(new LatLng(43.56497,1.46513));
        listPapier.add(new LatLng(43.56504,1.46529));
        listPapier.add(new LatLng(43.56461,1.4657));
        listPapier.add(new LatLng(43.56454,1.46554));
        listPapier.add(new LatLng(43.56542,1.46363));
        listPapier.add(new LatLng(43.56554,1.4639));
        listPapier.add(new LatLng(43.5652,1.46422));
        listPapier.add(new LatLng(43.56508,1.46391));
        listPapier.add(new LatLng(43.56372,1.46478));
        listPapier.add(new LatLng(43.56393,1.46529));
        listPapier.add(new LatLng(43.56426,1.46496));
        listPapier.add(new LatLng(43.56403,1.46448));
        listPapier.add(new LatLng(43.56349,1.46428));
        listPapier.add(new LatLng(43.56362,1.4646));
        listPapier.add(new LatLng(43.56323,1.46499));
        listPapier.add(new LatLng(43.56311,1.46473));
        listPapier.add(new LatLng(43.5632,1.465620));
        listPapier.add(new LatLng(43.56335,1.4659));
        listPapier.add(new LatLng(43.56263,1.46661));
        listPapier.add(new LatLng(43.56249,1.46632));
        listPapier.add(new LatLng(43.56377,1.46168));
        listPapier.add(new LatLng(43.56384,1.46181));
        listPapier.add(new LatLng(43.56375,1.46195));
        listPapier.add(new LatLng(43.56367,1.4618));
        listPapier.add(new LatLng(43.56123,1.46364));
        listPapier.add(new LatLng(43.56137,1.46392));
        listPapier.add(new LatLng(43.56086,1.46442));
        listPapier.add(new LatLng(43.56072,1.46414));
        listPapier.add(new LatLng(43.56151,1.46595));
        listPapier.add(new LatLng(43.56161,1.46623));
        listPapier.add(new LatLng(43.56186,1.46599));
        listPapier.add(new LatLng(43.56175,1.46571));
        listPapier.add(new LatLng(43.56146,1.46600));
        listPapier.add(new LatLng(43.56154,1.46615));
        listPapier.add(new LatLng(43.56112,1.46656));
        listPapier.add(new LatLng(43.56104,1.46641));
        listPapier.add(new LatLng(43.56202,1.46603));
        listPapier.add(new LatLng(43.5621,1.46618));
        listPapier.add(new LatLng(43.5615,1.46678));
        listPapier.add(new LatLng(43.56142,1.46663));
        listPapier.add(new LatLng(43.56146,1.46709));
        listPapier.add(new LatLng(43.56156,1.4673));
        listPapier.add(new LatLng(43.5614,1.46745));
        listPapier.add(new LatLng(43.5613,1.46725));
        listPapier.add(new LatLng(43.56245,1.4669));
        listPapier.add(new LatLng(43.56253,1.46706));
        listPapier.add(new LatLng(43.5623,1.46728));
        listPapier.add(new LatLng(43.56222,1.46713));
        listPapier.add(new LatLng(43.5618,1.4677));
        listPapier.add(new LatLng(43.56197,1.46805));
        listPapier.add(new LatLng(43.56174,1.46827));
        listPapier.add(new LatLng(43.56157,1.46793));
        listPapier.add(new LatLng(43.56263,1.46908));
        listPapier.add(new LatLng(43.56277,1.46939));
        listPapier.add(new LatLng(43.5625,1.46965));
        listPapier.add(new LatLng(43.56236,1.46932));
        listPapier.add(new LatLng(43.56189,1.46903));
        listPapier.add(new LatLng(43.56201,1.4693));
        listPapier.add(new LatLng(43.56185,1.46944));
        listPapier.add(new LatLng(43.56173,1.46916));
        listPapier.add(new LatLng(43.56134,1.46839));
        listPapier.add(new LatLng(43.56141,1.46855));
        listPapier.add(new LatLng(43.56101,1.46896));
        listPapier.add(new LatLng(43.56093,1.46881));
        listPapier.add(new LatLng(43.56141,1.46894));
        listPapier.add(new LatLng(43.56169,1.46951));
        listPapier.add(new LatLng(43.56105,1.47007));
        listPapier.add(new LatLng(43.56079,1.46955));
        listPapier.add(new LatLng(43.56153,1.47001));
        listPapier.add(new LatLng(43.56088,1.47064));
        listPapier.add(new LatLng(43.56107,1.47108));
        listPapier.add(new LatLng(43.56173,1.47041));
        listPapier.add(new LatLng(43.55933,1.47231));
        listPapier.add(new LatLng(43.55939,1.47246));
        listPapier.add(new LatLng(43.55886,1.47299));
        listPapier.add(new LatLng(43.55879,1.47284));
        listPapier.add(new LatLng(43.56076,1.46724));
        listPapier.add(new LatLng(43.56084,1.46739));
        listPapier.add(new LatLng(43.55942,1.46883));
        listPapier.add(new LatLng(43.55934,1.46868));
        listPapier.add(new LatLng(43.56025,1.46732));
        listPapier.add(new LatLng(43.56032,1.46748));
        listPapier.add(new LatLng(43.55976,1.46805));
        listPapier.add(new LatLng(43.55968,1.46787));
        listPapier.add(new LatLng(43.55844,1.46891));
        listPapier.add(new LatLng(43.55852,1.46906));
        listPapier.add(new LatLng(43.55798,1.4696));
        listPapier.add(new LatLng(43.5579,1.46945));

        for (LatLng position : listPapier) {
            interetPapier.add(new MarkerOptions()
                    .position(position)
                    .title(PointInteretType.PAPIER.toString())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(PointInteretType.PAPIER.getColor())));
        }

        // Verre
        List<LatLng> listVerre = new ArrayList<>();

        listVerre.add(new LatLng(43.55891,1.47303));
        listVerre.add(new LatLng(43.55999,1.47194));
        listVerre.add(new LatLng(43.55995,1.47195));
        listVerre.add(new LatLng(43.56439,1.47053));
        listVerre.add(new LatLng(43.56355,1.47579));
        listVerre.add(new LatLng(43.55509,1.46816));
        listVerre.add(new LatLng(43.56295,1.46311));
        listVerre.add(new LatLng(43.56305,1.45939));
        listVerre.add(new LatLng(43.56068,1.45738));
        listVerre.add(new LatLng(43.56376,1.45531));
        listVerre.add(new LatLng(43.56850,1.46620));
        listVerre.add(new LatLng(43.56735,1.46726));
        listVerre.add(new LatLng(43.57124,1.46269));
        listVerre.add(new LatLng(43.56731,1.46477));

        for (LatLng position : listVerre) {
            interetVerre.add(new MarkerOptions()
                    .position(position)
                    .title(PointInteretType.VERRE.toString())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(PointInteretType.VERRE.getColor())));
        }
    }
}
