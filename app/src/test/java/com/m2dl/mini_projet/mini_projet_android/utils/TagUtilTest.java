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
