/* This file is part of UPS-Caring.

    Copyright 2016 Charles Fallourd <charles.fallourd@master-developpement-logiciel.fr>
    Copyright 2016 Lucas Bled <lucas.bled@master-developpement-logiciel.fr>
    Copyright 2016 Théo Vaucher <theo.vaucher@master-developpement-logiciel.fr>

    UPS-Caring is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UPS-Caring is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UPS-Caring.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.m2dl.mini_projet.mini_projet_android.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.m2dl.mini_projet.mini_projet_android.R;
import com.m2dl.mini_projet.mini_projet_android.data.tag.PointInteret;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PointInteretManager {

    private Context context;
    private Map<Tag, List<Object>> mapPointInteret;

    public PointInteretManager(Context context) {
        this.context = context;
        init();
    }

    public Set<Tag> getPointInterets() {
        return mapPointInteret.keySet();
    }

    public Map<Tag, List<Object>> getMapPointInteret() {
        return mapPointInteret;
    }

    private void addPoint(PointInteret pointInteret, LatLng... positions) {
        Object opt;
        if (positions.length == 1) {
            opt = new CircleOptions()
                    .strokeColor(pointInteret.getColor())
                    .fillColor(pointInteret.getColor())
                    .center(positions[0])
                    .radius(5)
                    .zIndex(10);
        } else {
            opt = new PolygonOptions()
                    .strokeColor(pointInteret.getColor())
                    .fillColor(pointInteret.getColor())
                    .add(positions);
        }

        mapPointInteret.get(pointInteret).add(opt);
    }

    private void init() {
        mapPointInteret = new HashMap<>();

        // Carton
        PointInteret pointInteretCarton = new PointInteret("carton",
                ContextCompat.getColor(context, R.color.carton));
        mapPointInteret.put(pointInteretCarton, new ArrayList<>());

        addPoint(pointInteretCarton, new LatLng(43.55773,1.46920),
                new LatLng(43.55780,1.46934),
                new LatLng(43.55745,1.46969),
                new LatLng(43.55738,1.46954));
        addPoint(pointInteretCarton, new LatLng(43.55992,1.47172),
                new LatLng(43.56000,1.47186),
                new LatLng(43.55948,1.47236),
                new LatLng(43.55942,1.47220));
        addPoint(pointInteretCarton, new LatLng(43.5622,1.46751),
                new LatLng(43.56231,1.4677),
                new LatLng(43.56206,1.46795),
                new LatLng(43.56197,1.46772));
        addPoint(pointInteretCarton, new LatLng(43.56449,1.4656),
                new LatLng(43.56455,1.46576),
                new LatLng(43.56406,1.46625),
                new LatLng(43.56398,1.46609));
        addPoint(pointInteretCarton, new LatLng(43.56577,1.46736),
                new LatLng(43.56593,1.46769),
                new LatLng(43.56577,1.46785),
                new LatLng(43.5656,1.46753));
        addPoint(pointInteretCarton, new LatLng(43.56665,1.4695),
                new LatLng(43.56674,1.46968),
                new LatLng(43.56657,1.46985),
                new LatLng(43.5665,1.46965));

        // Piles
        PointInteret pointInteretPiles = new PointInteret("piles",
                ContextCompat.getColor(context, R.color.piles));
        mapPointInteret.put(pointInteretPiles, new ArrayList<>());

        addPoint(pointInteretPiles, new LatLng(43.56362,1.46487),
                new LatLng(43.56381,1.46537),
                new LatLng(43.56354,1.4657),
                new LatLng(43.5633,1.46522));

        addPoint(pointInteretPiles, new LatLng(43.56456,1.46589),
                new LatLng(43.56472,1.46617),
                new LatLng(43.56458,1.46629),
                new LatLng(43.56446,1.46602));

        // Textiles
        PointInteret pointInteretTextiles = new PointInteret("textiles",
                ContextCompat.getColor(context, R.color.textiles));
        mapPointInteret.put(pointInteretTextiles, new ArrayList<>());

        addPoint(pointInteretTextiles, new LatLng(43.55505,1.46811));
        addPoint(pointInteretTextiles, new LatLng(43.56305,1.45935));

        // Papier
        PointInteret pointInterePapier = new PointInteret("papier",
                ContextCompat.getColor(context, R.color.papier));
        mapPointInteret.put(pointInterePapier, new ArrayList<>());

        addPoint(pointInterePapier, new LatLng(43.56758,1.46950),
                new LatLng(43.56773,1.46989),
                new LatLng(43.56733,1.47026),
                new LatLng(43.56716,1.46991));
        addPoint(pointInterePapier, new LatLng(43.56666,1.46950),
                new LatLng(43.56674,1.46967),
                new LatLng(43.56667,1.46975),
                new LatLng(43.56657,1.46958));
        addPoint(pointInterePapier, new LatLng(43.56497,1.46513),
                new LatLng(43.56504,1.46529),
                new LatLng(43.56461,1.4657),
                new LatLng(43.56454,1.46554));
        addPoint(pointInterePapier, new LatLng(43.56542,1.46363),
                new LatLng(43.56554,1.4639),
                new LatLng(43.5652,1.46422),
                new LatLng(43.56508,1.46391));
        addPoint(pointInterePapier, new LatLng(43.56372,1.46478),
                new LatLng(43.56393,1.46529),
                new LatLng(43.56426,1.46496),
                new LatLng(43.56403,1.46448));
        addPoint(pointInterePapier, new LatLng(43.56349,1.46428),
                new LatLng(43.56362,1.4646),
                new LatLng(43.56323,1.46499),
                new LatLng(43.56311,1.46473));
        addPoint(pointInterePapier, new LatLng(43.5632,1.465620),
                new LatLng(43.56335,1.4659),
                new LatLng(43.56263,1.46661),
                new LatLng(43.56249,1.46632));
        addPoint(pointInterePapier, new LatLng(43.56377,1.46168),
                new LatLng(43.56384,1.46181),
                new LatLng(43.56375,1.46195),
                new LatLng(43.56367,1.4618));
        addPoint(pointInterePapier, new LatLng(43.56123,1.46364),
                new LatLng(43.56137,1.46392),
                new LatLng(43.56086,1.46442),
                new LatLng(43.56072,1.46414));
        addPoint(pointInterePapier, new LatLng(43.56151,1.46595),
                new LatLng(43.56161,1.46623),
                new LatLng(43.56186,1.46599),
                new LatLng(43.56175,1.46571));
        addPoint(pointInterePapier, new LatLng(43.56146,1.46600),
                new LatLng(43.56154,1.46615),
                new LatLng(43.56112,1.46656),
                new LatLng(43.56104,1.46641));
        addPoint(pointInterePapier, new LatLng(43.56202,1.46603),
                new LatLng(43.5621,1.46618),
                new LatLng(43.5615,1.46678),
                new LatLng(43.56142,1.46663));
        addPoint(pointInterePapier, new LatLng(43.56146,1.46709),
                new LatLng(43.56156,1.4673),
                new LatLng(43.5614,1.46745),
                new LatLng(43.5613,1.46725));
        addPoint(pointInterePapier, new LatLng(43.56245,1.4669),
                new LatLng(43.56253,1.46706),
                new LatLng(43.5623,1.46728),
                new LatLng(43.56222,1.46713));
        addPoint(pointInterePapier, new LatLng(43.5618,1.4677),
                new LatLng(43.56197,1.46805),
                new LatLng(43.56174,1.46827),
                new LatLng(43.56157,1.46793));
        addPoint(pointInterePapier, new LatLng(43.56263,1.46908),
                new LatLng(43.56277,1.46939),
                new LatLng(43.5625,1.46965),
                new LatLng(43.56236,1.46932));
        addPoint(pointInterePapier, new LatLng(43.56189,1.46903),
                new LatLng(43.56201,1.4693),
                new LatLng(43.56185,1.46944),
                new LatLng(43.56173,1.46916));
        addPoint(pointInterePapier, new LatLng(43.56134,1.46839),
                new LatLng(43.56141,1.46855),
                new LatLng(43.56101,1.46896),
                new LatLng(43.56093,1.46881));
        addPoint(pointInterePapier, new LatLng(43.56141,1.46894),
                new LatLng(43.56169,1.46951),
                new LatLng(43.56105,1.47007),
                new LatLng(43.56079,1.46955));
        addPoint(pointInterePapier, new LatLng(43.56153,1.47001),
                new LatLng(43.56088,1.47064),
                new LatLng(43.56107,1.47108),
                new LatLng(43.56173,1.47041));
        addPoint(pointInterePapier, new LatLng(43.55933,1.47231),
                new LatLng(43.55939,1.47246),
                new LatLng(43.55886,1.47299),
                new LatLng(43.55879,1.47284));
        addPoint(pointInterePapier, new LatLng(43.56076,1.46724),
                new LatLng(43.56084,1.46739),
                new LatLng(43.55942,1.46883),
                new LatLng(43.55934,1.46868));
        addPoint(pointInterePapier, new LatLng(43.56025,1.46732),
                new LatLng(43.56032,1.46748),
                new LatLng(43.55976,1.46805),
                new LatLng(43.55968,1.46787));
        addPoint(pointInterePapier, new LatLng(43.55844,1.46891),
                new LatLng(43.55852,1.46906),
                new LatLng(43.55798,1.4696),
                new LatLng(43.5579,1.46945));

        // Verre
        PointInteret pointIntereVerre = new PointInteret("verre",
                ContextCompat.getColor(context, R.color.verre));
        mapPointInteret.put(pointIntereVerre, new ArrayList<>());

        addPoint(pointIntereVerre, new LatLng(43.55891,1.47303));
        addPoint(pointIntereVerre, new LatLng(43.55999,1.47194));
        addPoint(pointIntereVerre, new LatLng(43.55995,1.47195));
        addPoint(pointIntereVerre, new LatLng(43.56439,1.47053));
        addPoint(pointIntereVerre, new LatLng(43.56355,1.47579));
        addPoint(pointIntereVerre, new LatLng(43.55509,1.46816));
        addPoint(pointIntereVerre, new LatLng(43.56295,1.46311));
        addPoint(pointIntereVerre, new LatLng(43.56305,1.45939));
        addPoint(pointIntereVerre, new LatLng(43.56068,1.45738));
        addPoint(pointIntereVerre, new LatLng(43.56376,1.45531));
        addPoint(pointIntereVerre, new LatLng(43.56850,1.46620));
        addPoint(pointIntereVerre, new LatLng(43.56735,1.46726));
        addPoint(pointIntereVerre, new LatLng(43.57124,1.46269));
        addPoint(pointIntereVerre, new LatLng(43.56731,1.46477));
    }
}
