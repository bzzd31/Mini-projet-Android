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
package com.m2dl.mini_projet.mini_projet_android.photos.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.m2dl.mini_projet.mini_projet_android.photos.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CloudinaryHelper {
    public static Cloudinary cloudinary = new Cloudinary(Constants.CLOUDINARY_URL);

    @SuppressWarnings("unchecked")
    public static String upload(InputStream inputStream) {
        try {


            Map<String,String> result = cloudinary.uploader().upload(inputStream, ObjectUtils.asMap("sslmode", "require"));
            return result.get("public_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
