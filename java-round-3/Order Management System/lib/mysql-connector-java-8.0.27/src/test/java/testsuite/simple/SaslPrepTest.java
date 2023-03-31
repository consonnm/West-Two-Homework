/*
 * Copyright (c) 2020, Oracle and/or its affiliates.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package testsuite.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.SaslPrep;
import com.mysql.cj.util.SaslPrep.StringType;

import testsuite.BaseTestCase;

public class SaslPrepTest extends BaseTestCase {
    private static String PROHIBITED_MSG1 = "Prohibited character at position 0\\.";
    private static String PROHIBITED_MSG2 = "Prohibited character at position 4\\.";
    private static String PROHIBITED_MSG3 = "Prohibited character at position 8\\.";
    private static String BIDI_MSG1 = "Cannot contain both RandALCat characters and LCat characters\\.";
    private static String BIDI_MSG2 = "Cannot contain RandALCat characters and not start and end with RandALCat characters\\.";
    private static String UNASSIGNED_MSG1 = "Unassigned character at position 0\\.";
    private static String UNASSIGNED_MSG2 = "Unassigned character at position 4\\.";
    private static String UNASSIGNED_MSG3 = "Unassigned character at position 8\\.";

    /**
     * Valid string.
     */
    @Test
    public void safeString() {
        assertEquals("my,0TEXT", SaslPrep.prepare("my,0TEXT", StringType.QUERY));
        assertEquals("my,0TEXT", SaslPrep.prepare("my,0TEXT", StringType.STORED));
        assertEquals("my,0 TEXT", SaslPrep.prepare("my,0 TEXT", StringType.QUERY));
        assertEquals("my,0 TEXT", SaslPrep.prepare("my,0 TEXT", StringType.STORED));
    }

    /**
     * Mapping: non-ASCII space characters.
     */
    @Test
    public void mappingNonAsciiSpaceCharacters() {
        assertEquals("my,0 TEXT", SaslPrep.prepare("my,0\u1680TEXT", StringType.QUERY));
        assertEquals("my,0 TEXT", SaslPrep.prepare("my,0\u1680TEXT", StringType.STORED));
        assertEquals("my,0 TEXT", SaslPrep.prepare("my,0\u200BTEXT", StringType.QUERY));
        assertEquals("my,0 TEXT", SaslPrep.prepare("my,0\u200BTEXT", StringType.STORED));
        assertEquals(" my,0 TEXT ", SaslPrep.prepare("\u00A0my,0\u2000TEXT\u3000", StringType.QUERY));
        assertEquals(" my,0 TEXT ", SaslPrep.prepare("\u00A0my,0\u2000TEXT\u3000", StringType.STORED));
    }

    /**
     * Mapping: the "commonly mapped to nothing" characters.
     */
    @Test
    public void mappingMappeableToNothing() {
        assertEquals("my,0TEXT", SaslPrep.prepare("my,0\u00ADTEXT", StringType.QUERY));
        assertEquals("my,0TEXT", SaslPrep.prepare("my,0\u00ADTEXT", StringType.STORED));
        assertEquals("my,0TEXT", SaslPrep.prepare("my,0\uFE0ATEXT", StringType.QUERY));
        assertEquals("my,0TEXT", SaslPrep.prepare("my,0\uFE0ATEXT", StringType.STORED));
        assertEquals("my,0TEXT", SaslPrep.prepare("\u00ADmy,0\u1806TE\uFE0FXT\uFEFF", StringType.QUERY));
        assertEquals("my,0TEXT", SaslPrep.prepare("\u00ADmy,0\u1806TE\uFE0FXT\uFEFF", StringType.STORED));
    }

    /**
     * KC Normalization.
     */
    @Test
    public void kcNormalization() {
        assertEquals("my,0 fi TEXT", SaslPrep.prepare("my,0 \uFB01 TEXT", StringType.QUERY));
        assertEquals("my,0 fi TEXT", SaslPrep.prepare("my,0 \uFB01 TEXT", StringType.STORED));
    }

    /**
     * Prohibited Output: ASCII control characters.
     */
    @Test
    public void prohibitedOutputAsciiControlCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\u007Fmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\u007Fmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u001FTEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u001FTEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u0000", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u0000", StringType.STORED));
    }

    /**
     * Prohibited Output: non-ASCII control characters.
     */
    @Test
    public void prohibitedOutputNonAsciiControlCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uFFFCmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uFFFCmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u008DTEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u008DTEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uD834\uDD73TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uD834\uDD73TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u2028", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u2028", StringType.STORED));
    }

    /**
     * Prohibited Output: private use characters.
     */
    @Test
    public void prohibitedPrivateUseCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uE000my,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uE000my,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uF8FFTEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uF8FFTEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uDBC0\uDC00TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uDBC0\uDC00TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uDB80\uDC46", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uDB80\uDC46", StringType.STORED));
    }

    /**
     * Prohibited Output: non-character code points.
     */
    @Test
    public void prohibitedNonCharacterCodePoints() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uDB3F\uDFFFmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uDB3F\uDFFFmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uFDD0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uFDD0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uD9FF\uDFFETEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uD9FF\uDFFETEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uDBBF\uDFFF", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uDBBF\uDFFF", StringType.STORED));
    }

    /**
     * Prohibited Output: surrogate code points.
     */
    @Test
    public void prohibitedSurrogateCodes() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uD83D\uDC2Cmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uD83D\uDC2Cmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uD83C\uDF63TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uD83C\uDF63TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uD83C\uDF7B", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uD83C\uDF7B", StringType.STORED));
    }

    /**
     * Prohibited Output: inappropriate for plain text characters.
     */
    @Test
    public void prohibitedInappropriateForPlainTextCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uFFFACmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uFFFAmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uFFFDTEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uFFFDTEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uFFFC", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uFFFC", StringType.STORED));
    }

    /**
     * Prohibited Output: inappropriate for canonical representation characters.
     */
    @Test
    public void prohibitedInappropriateForCanonicalRepresentationCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\u2FF0my,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\u2FF0my,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u2FFBTEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u2FFBTEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u2FF8", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u2FF8", StringType.STORED));
    }

    /**
     * Prohibited Output: change display properties or deprecated characters.
     */
    @Test
    public void prohibitedChangeDisplayPropertiesOrDeprecatedCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\u206Fmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\u206Fmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u200ETEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\u200ETEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u202E", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u202E", StringType.STORED));
    }

    /**
     * Prohibited Output: tagging characters.
     */
    @Test
    public void prohibitedTaggingCharacters() {
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uDB40\uDC7Fmy,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG1, () -> SaslPrep.prepare("\uDB40\uDC7Fmy,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uDB40\uDC21TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG2, () -> SaslPrep.prepare("my,0\uDB40\uDC21TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uDB40\uDC01", StringType.QUERY));
        assertThrows(WrongArgumentException.class, PROHIBITED_MSG3, () -> SaslPrep.prepare("my,0TEXT\uDB40\uDC01", StringType.STORED));
    }

    /**
     * Bidirectional Characters.
     */
    @Test
    public void bidiCombinations() {
        // If a string contains any RandALCat character, the string MUST NOT contain any LCat character.
        assertThrows(WrongArgumentException.class, BIDI_MSG1, () -> SaslPrep.prepare("\u0627my,0TEXT", StringType.QUERY));
        assertThrows(WrongArgumentException.class, BIDI_MSG1, () -> SaslPrep.prepare("\u0627my,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, BIDI_MSG1, () -> SaslPrep.prepare("my,0TEXT\u064A", StringType.QUERY));
        assertThrows(WrongArgumentException.class, BIDI_MSG1, () -> SaslPrep.prepare("my,0TEXT\u064A", StringType.STORED));
        assertThrows(WrongArgumentException.class, BIDI_MSG1, () -> SaslPrep.prepare("\u0627my,0\u0628TEXT\u064A", StringType.QUERY));
        assertThrows(WrongArgumentException.class, BIDI_MSG1, () -> SaslPrep.prepare("\u0627my,0\u0628TEXT\u064A", StringType.STORED));

        // If a string contains any RandALCat character, a RandALCat character MUST be the first character of the string, and a RandALCat character MUST be the
        // last character of the string.
        assertThrows(WrongArgumentException.class, BIDI_MSG2, () -> SaslPrep.prepare("\u0627-1\u0628-2-3-4", StringType.QUERY));
        assertThrows(WrongArgumentException.class, BIDI_MSG2, () -> SaslPrep.prepare("\u0627-1\u0628-2-3-4", StringType.STORED));
        assertThrows(WrongArgumentException.class, BIDI_MSG2, () -> SaslPrep.prepare("-1-2-3-4\u064A", StringType.QUERY));
        assertThrows(WrongArgumentException.class, BIDI_MSG2, () -> SaslPrep.prepare("-1-2-3-4\u064A", StringType.STORED));

        // Fine, as long as starts and ends with RandALCat characters and doesn't contain any LCat character.
        assertEquals("\u0627-1-2-3-4\u064A", SaslPrep.prepare("\u0627-1-2-3-4\u064A", StringType.QUERY));
        assertEquals("\u0627-1-2-3-4\u064A", SaslPrep.prepare("\u0627-1-2-3-4\u064A", StringType.STORED));
        assertEquals("\u0627-1\u0628-2-3-4\u064A", SaslPrep.prepare("\u0627-1\u0628-2-3-4\u064A", StringType.QUERY));
        assertEquals("\u0627-1\u0628-2-3-4\u064A", SaslPrep.prepare("\u0627-1\u0628-2-3-4\u064A", StringType.STORED));
    }

    /**
     * Unassigned Code Points.
     */
    @Test
    public void unassignedCodePoints() {
        // Queries for matching strings MAY contain unassigned code points.
        assertEquals("\u0888my,0TEXT", SaslPrep.prepare("\u0888my,0TEXT", StringType.QUERY));
        assertEquals("my,0\u0890TEXT", SaslPrep.prepare("my,0\u0890TEXT", StringType.QUERY));
        assertEquals("my,0TEXT\u089F", SaslPrep.prepare("my,0TEXT\u089F", StringType.QUERY));

        // Stored strings using the profile MUST NOT contain any unassigned code points.
        assertThrows(WrongArgumentException.class, UNASSIGNED_MSG1, () -> SaslPrep.prepare("\u0888my,0TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, UNASSIGNED_MSG2, () -> SaslPrep.prepare("my,0\u0890TEXT", StringType.STORED));
        assertThrows(WrongArgumentException.class, UNASSIGNED_MSG3, () -> SaslPrep.prepare("my,0TEXT\u089F", StringType.STORED));
    }
}
