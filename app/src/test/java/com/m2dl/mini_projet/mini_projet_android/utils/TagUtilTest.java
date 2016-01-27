/* This file is part of UPS-Caring.

    Copyright 2016 Charles Falloud <charles.fallourd@master-developpement-logiciel.fr>
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

import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TagUtilTest {

    @Test
    public void shouldContains() throws Exception {
        Set<Tag> tag = new TreeSet<>(Arrays.asList(
                new Tag("tag1"),
                new Tag("tag2"),
                new Tag("tag3"),
                new Tag("tag4"))
        );


        assertTrue(TagUtil.containsOneOf(tag, Arrays.asList(new Tag("test1"), new Tag("tag1"))));
        assertTrue(TagUtil.containsOneOf(tag, Arrays.asList(new Tag("tag4"), new Tag("tag1"))));
        assertTrue(TagUtil.containsOneOf(tag, Arrays.asList(new Tag("tag4"), new Tag("test1"))));
    }

    @Test
    public void shouldNotContains() throws Exception {
        Set<Tag> tag = new TreeSet<>(Arrays.asList(
                new Tag("tag1"),
                new Tag("tag2"),
                new Tag("tag3"),
                new Tag("tag4"))
        );

        assertFalse(TagUtil.containsOneOf(tag, Arrays.asList(new Tag("test1"), new Tag("test2"))));
        assertFalse(TagUtil.containsOneOf(tag, Arrays.asList(new Tag("tag5"), new Tag("test3"))));
        assertFalse(TagUtil.containsOneOf(tag, Arrays.asList(new Tag("test1"), new Tag("test40"))));
    }
}
