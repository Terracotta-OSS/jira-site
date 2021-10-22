/**
 *  Copyright Terracotta, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.sf.ehcache.pool.sizeof;

import net.sf.ehcache.util.FindBugsSuppressWarnings;

import java.awt.RenderingHints;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleState;
import javax.print.DocFlavor;
import javax.print.attribute.standard.MediaSize;
import javax.swing.plaf.synth.Region;
import javax.swing.text.html.HTML;
import javax.xml.datatype.DatatypeConstants;

/**
 * Enum with all the flyweight types that we check for sizeOf measurements
 *
 * @author Alex Snaps
 * @author C&eacute;drik Lime
 */
@FindBugsSuppressWarnings("RC_REF_COMPARISON")
enum FlyweightType {

    /**
     * java.lang.Enum
     */
    ENUM(Enum.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.print.attribute.EnumSyntax
     */
    ENUM_SYNTAX(javax.print.attribute.EnumSyntax.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.lang.Class
     */
    CLASS(Class.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    // XXX There is no nullipotent way of determining the interned status of a string
    // There are numerous String constants within the JDK (see list at http://docs.oracle.com/javase/7/docs/api/constant-values.html),
    // but enumerating all of them would lead to _lots_ of == tests.
    /**
     * java.lang.String
     */
    //STRING(String.class) {
    //    @Override
    //    boolean isShared(final Object obj) { return obj == ((String)obj).intern(); }
    //},
    /**
     * java.lang.Boolean
     */
    BOOLEAN(Boolean.class) {
        @Override
        boolean isShared(final Object obj) { return obj == Boolean.TRUE || obj == Boolean.FALSE; }
    },
    /**
     * java.lang.Byte
     */
    BYTE(Byte.class) {
        @Override
        boolean isShared(final Object obj) { return obj == Byte.valueOf((Byte)obj); }
    },
    /**
     * java.lang.Short
     */
    SHORT(Short.class) {
        @Override
        boolean isShared(final Object obj) {
            short value = ((Short)obj).shortValue();
            return value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE && obj == Short.valueOf(value);
        }
    },
    /**
     * java.lang.Integer
     */
    INTEGER(Integer.class) {
        @Override
        boolean isShared(final Object obj) {
            int value = ((Integer)obj).intValue();
            return (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE && obj == Integer.valueOf(value))
                    || obj == javax.swing.JLayeredPane.DEFAULT_LAYER || obj == javax.swing.JLayeredPane.PALETTE_LAYER
                    || obj == javax.swing.JLayeredPane.MODAL_LAYER   || obj == javax.swing.JLayeredPane.POPUP_LAYER
                    || obj == javax.swing.JLayeredPane.DRAG_LAYER    || obj == javax.swing.JLayeredPane.FRAME_CONTENT_LAYER;
        }
    },
    /**
     * java.lang.Long
     */
    LONG(Long.class) {
        @Override
        boolean isShared(final Object obj) {
            long value = ((Long)obj).longValue();
            return value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE && obj == Long.valueOf(value);
        }
    },
    /**
     * java.lang.Float
     */
    FLOAT(Float.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.font.TextAttribute.WEIGHT_EXTRA_LIGHT       || obj == java.awt.font.TextAttribute.WEIGHT_LIGHT
                    || obj == java.awt.font.TextAttribute.WEIGHT_DEMILIGHT     || obj == java.awt.font.TextAttribute.WEIGHT_REGULAR
                    || obj == java.awt.font.TextAttribute.WEIGHT_SEMIBOLD      || obj == java.awt.font.TextAttribute.WEIGHT_MEDIUM
                    || obj == java.awt.font.TextAttribute.WEIGHT_DEMIBOLD      || obj == java.awt.font.TextAttribute.WEIGHT_BOLD
                    || obj == java.awt.font.TextAttribute.WEIGHT_HEAVY         || obj == java.awt.font.TextAttribute.WEIGHT_EXTRABOLD
                    || obj == java.awt.font.TextAttribute.WEIGHT_ULTRABOLD     || obj == java.awt.font.TextAttribute.WIDTH_CONDENSED
                    || obj == java.awt.font.TextAttribute.WIDTH_SEMI_CONDENSED || obj == java.awt.font.TextAttribute.WIDTH_REGULAR
                    || obj == java.awt.font.TextAttribute.WIDTH_SEMI_EXTENDED  || obj == java.awt.font.TextAttribute.WIDTH_EXTENDED
                    || obj == java.awt.font.TextAttribute.POSTURE_REGULAR      || obj == java.awt.font.TextAttribute.POSTURE_OBLIQUE
                    || obj == java.awt.font.TextAttribute.JUSTIFICATION_FULL   || obj == java.awt.font.TextAttribute.JUSTIFICATION_NONE;
//                    || obj == java.awt.font.TextAttribute.TRACKING_TIGHT       || obj == java.awt.font.TextAttribute.TRACKING_LOOSE;
        }
    },
    /**
     * java.math.BigInteger
     */
    BIGINTEGER(java.math.BigInteger.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == BigInteger.ZERO || obj == BigInteger.ONE || obj == BigInteger.TEN
                    || obj == java.security.spec.RSAKeyGenParameterSpec.F0 || obj == java.security.spec.RSAKeyGenParameterSpec.F4;
        }
    },
    /**
     * java.math.BigDecimal
     */
    BIGDECIMAL(java.math.BigDecimal.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == BigDecimal.ZERO || obj == BigDecimal.ONE || obj == BigDecimal.TEN;
        }
    },
    /**
     * java.math.MathContext
     */
    MATHCONTEXT(java.math.MathContext.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == MathContext.UNLIMITED || obj == MathContext.DECIMAL32 || obj == MathContext.DECIMAL64 || obj == MathContext.DECIMAL128;
        }
    },
    /**
     * java.lang.Character
     */
    CHARACTER(Character.class) {
        @Override
        boolean isShared(final Object obj) { return ((Character)obj).charValue() <= Byte.MAX_VALUE && obj == Character.valueOf((Character)obj); }
    },
    /**
     * java.lang.Character.UnicodeBlock
     */
    CHARACTER_UNICODEBLOCK(Character.UnicodeBlock.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.util.Locale
     */
    LOCALE(Locale.class) {
        @Override
        boolean isShared(final Object obj) {
            return GLOBAL_LOCALES.contains(obj);
        }
    },
    /**
     * java.io.FileDescriptor
     */
    FILEDESCRIPTOR(java.io.FileDescriptor.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.io.FileDescriptor.in || obj == java.io.FileDescriptor.out || obj == java.io.FileDescriptor.err;
        }
    },
    /**
     * java.net.Proxy
     */
    PROXY(java.net.Proxy.class) {
        @Override
        boolean isShared(final Object obj) { return obj == java.net.Proxy.NO_PROXY; }
    },
    /**
     * java.nio.ByteOrder
     */
    BYTEORDER(java.nio.ByteOrder.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.nio.channels.FileChannel.MapMode
     */
    FILECHANNEL_MAPMODE(java.nio.channels.FileChannel.MapMode.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.nio.charset.CoderResult
     */
    CODERRESULT(java.nio.charset.CoderResult.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.nio.charset.CodingErrorAction
     */
    CODINGERRORACTION(java.nio.charset.CodingErrorAction.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.security.spec.ECPoint
     */
    ECPOINT(java.security.spec.ECPoint.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.security.spec.MGF1ParameterSpec
     */
    MGF1PARAMETERSPEC(java.security.spec.MGF1ParameterSpec.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.security.spec.MGF1ParameterSpec.SHA1 || obj == java.security.spec.MGF1ParameterSpec.SHA256
                    || obj == java.security.spec.MGF1ParameterSpec.SHA384 || obj == java.security.spec.MGF1ParameterSpec.SHA512;
        }
    },
    /**
     * java.security.spec.PSSParameterSpec
     */
    PSSPARAMETERSPEC(java.security.spec.PSSParameterSpec.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.security.spec.PSSParameterSpec.DEFAULT;
        }
    },
    /**
     * java.text.AttributedCharacterIterator.Attribute
     */
    ATTRIBUTEDCHARACTERITERATOR_ATTRIBUTE(java.text.AttributedCharacterIterator.Attribute.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.text.AttributedCharacterIterator.Attribute.LANGUAGE
                    || obj == java.text.AttributedCharacterIterator.Attribute.READING
                    || obj == java.text.AttributedCharacterIterator.Attribute.INPUT_METHOD_SEGMENT;
        }
    },
    /**
     * java.text.DateFormat.Field
     */
    DATEFORMAT_FIELD(java.text.DateFormat.Field.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.text.MessageFormat.Field
     */
    MESSAGEFORMAT_FIELD(java.text.MessageFormat.Field.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.text.NumberFormat.Field
     */
    NUMBERFORMAT_FIELD(java.text.NumberFormat.Field.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.util.jar.Attributes.Name
     */
    ATTRIBUTES_NAME(java.util.jar.Attributes.Name.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.util.jar.Attributes.Name.MANIFEST_VERSION   || obj == java.util.jar.Attributes.Name.SIGNATURE_VERSION
                    || obj == java.util.jar.Attributes.Name.CONTENT_TYPE   || obj == java.util.jar.Attributes.Name.CLASS_PATH
                    || obj == java.util.jar.Attributes.Name.MAIN_CLASS     || obj == java.util.jar.Attributes.Name.SEALED
                    || obj == java.util.jar.Attributes.Name.EXTENSION_LIST || obj == java.util.jar.Attributes.Name.EXTENSION_NAME
                    || obj == java.util.jar.Attributes.Name.EXTENSION_INSTALLATION
                    || obj == java.util.jar.Attributes.Name.IMPLEMENTATION_TITLE
                    || obj == java.util.jar.Attributes.Name.IMPLEMENTATION_VERSION
                    || obj == java.util.jar.Attributes.Name.IMPLEMENTATION_VENDOR
                    || obj == java.util.jar.Attributes.Name.IMPLEMENTATION_VENDOR_ID
                    || obj == java.util.jar.Attributes.Name.IMPLEMENTATION_URL
                    || obj == java.util.jar.Attributes.Name.SPECIFICATION_TITLE
                    || obj == java.util.jar.Attributes.Name.SPECIFICATION_VERSION
                    || obj == java.util.jar.Attributes.Name.SPECIFICATION_VENDOR;
        }
    },
    /**
     * java.util.logging.Level
     */
    LEVEL(java.util.logging.Level.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.util.logging.Level.OFF || obj == java.util.logging.Level.SEVERE || obj == java.util.logging.Level.WARNING
                    || obj == java.util.logging.Level.INFO || obj == java.util.logging.Level.CONFIG || obj == java.util.logging.Level.FINE
                    || obj == java.util.logging.Level.FINER || obj == java.util.logging.Level.FINEST || obj == java.util.logging.Level.ALL;
        }
    },
    /**
     * java.util.logging.Logger
     */
    LOGGER(java.util.logging.Logger.class) {
        @Override
        @SuppressWarnings("deprecation")
        boolean isShared(final Object obj) { return obj == java.util.logging.Logger.global; }
    },
    /**
     * java.awt.AlphaComposite
     */
    ALPHACOMPOSITE(java.awt.AlphaComposite.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.AlphaComposite.Clear
                    || obj == java.awt.AlphaComposite.Src
                    || obj == java.awt.AlphaComposite.Dst
                    || obj == java.awt.AlphaComposite.SrcOver
                    || obj == java.awt.AlphaComposite.DstOver
                    || obj == java.awt.AlphaComposite.SrcIn
                    || obj == java.awt.AlphaComposite.DstIn
                    || obj == java.awt.AlphaComposite.SrcOut
                    || obj == java.awt.AlphaComposite.DstOut
                    || obj == java.awt.AlphaComposite.SrcAtop
                    || obj == java.awt.AlphaComposite.DstAtop
                    || obj == java.awt.AlphaComposite.Xor;
        }
    },
    /**
     * java.awt.BufferCapabilities.FlipContents
     */
    BUFFERCAPABILITIES_FLIPCONTENTS(java.awt.BufferCapabilities.FlipContents.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.Color
     */
    COLOR(java.awt.Color.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.Color.WHITE
                    || obj == java.awt.Color.LIGHT_GRAY
                    || obj == java.awt.Color.GRAY
                    || obj == java.awt.Color.DARK_GRAY
                    || obj == java.awt.Color.BLACK
                    || obj == java.awt.Color.RED
                    || obj == java.awt.Color.PINK
                    || obj == java.awt.Color.ORANGE
                    || obj == java.awt.Color.YELLOW
                    || obj == java.awt.Color.GREEN
                    || obj == java.awt.Color.MAGENTA
                    || obj == java.awt.Color.CYAN
                    || obj == java.awt.Color.BLUE;
        }
    },
    /**
     * java.awt.ComponentOrientation
     */
    COMPONENTORIENTATION(java.awt.ComponentOrientation.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.Cursor
     */
    CURSOR(java.awt.Cursor.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.dnd.DragSource.DefaultCopyDrop
                    || obj == java.awt.dnd.DragSource.DefaultMoveDrop
                    || obj == java.awt.dnd.DragSource.DefaultLinkDrop
                    || obj == java.awt.dnd.DragSource.DefaultCopyNoDrop
                    || obj == java.awt.dnd.DragSource.DefaultMoveNoDrop
                    || obj == java.awt.dnd.DragSource.DefaultLinkNoDrop;
        }
    },
    /**
     * java.awt.JobAttributes.DefaultSelectionType
     */
    JOBATTRIBUTES_DEFAULTSELECTIONTYPE(java.awt.JobAttributes.DefaultSelectionType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.JobAttributes.DestinationType
     */
    JOBATTRIBUTES_DESTINATIONTYPE(java.awt.JobAttributes.DestinationType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.JobAttributes.DialogType
     */
    JOBATTRIBUTES_DIALOGTYPE(java.awt.JobAttributes.DialogType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.JobAttributes.MultipleDocumentHandlingType
     */
    JOBATTRIBUTES_MULTIPLEDOCUMENTHANDLINGTYPE(java.awt.JobAttributes.MultipleDocumentHandlingType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.JobAttributes.SidesType
     */
    JOBATTRIBUTES_SIDESTYPE(java.awt.JobAttributes.SidesType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.PageAttributes.ColorType
     */
    PAGEATTRIBUTES_COLORTYPE(java.awt.PageAttributes.ColorType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.PageAttributes.MediaType
     */
    PAGEATTRIBUTES_MEDIATYPE(java.awt.PageAttributes.MediaType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.PageAttributes.OrientationRequestedType
     */
    PAGEATTRIBUTES_ORIENTATIONREQUESTEDTYPE(java.awt.PageAttributes.OrientationRequestedType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.PageAttributes.OriginType
     */
    PAGEATTRIBUTES_ORIGINTYPE(java.awt.PageAttributes.OriginType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.PageAttributes.PrintQualityType
     */
    PAGEATTRIBUTES_PRINTQUALITYTYPE(java.awt.PageAttributes.PrintQualityType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.RenderingHints.Key
     */
    // Note: this is wrong, those are subclasses of .Key!
    RENDERINGHINTS_KEY(java.awt.RenderingHints.Key.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == RenderingHints.KEY_ANTIALIASING
                    || obj == RenderingHints.KEY_RENDERING
                    || obj == RenderingHints.KEY_DITHERING
                    || obj == RenderingHints.KEY_TEXT_ANTIALIASING
//                    || obj == RenderingHints.KEY_TEXT_LCD_CONTRAST
                    || obj == RenderingHints.KEY_FRACTIONALMETRICS
                    || obj == RenderingHints.KEY_INTERPOLATION
                    || obj == RenderingHints.KEY_ALPHA_INTERPOLATION
                    || obj == RenderingHints.KEY_COLOR_RENDERING
                    || obj == RenderingHints.KEY_STROKE_CONTROL;
        }
    },
    /**
     * java.awt.SystemColor
     */
    SYSTEMCOLOR(java.awt.SystemColor.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.datatransfer.DataFlavor
     */
    DATAFLAVOR(java.awt.datatransfer.DataFlavor.class) {
        @Override
        @SuppressWarnings("deprecation")
        boolean isShared(final Object obj) {
            return obj == java.awt.datatransfer.DataFlavor.stringFlavor
                    || obj == java.awt.datatransfer.DataFlavor.imageFlavor
                    || obj == java.awt.datatransfer.DataFlavor.plainTextFlavor
                    || obj == java.awt.datatransfer.DataFlavor.javaFileListFlavor;
        }
    },
    /**
     * java.awt.font.TextAttribute
     */
    TEXTATTRIBUTE(java.awt.font.TextAttribute.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * java.awt.font.TextLayout.CaretPolicy
     */
    TEXTLAYOUT_CARETPOLICY(java.awt.font.TextLayout.CaretPolicy.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.font.TextLayout.DEFAULT_CARET_POLICY;
        }
    },
    /**
     * java.awt.font.TransformAttribute
     *//*
    TRANSFORMATTRIBUTE(java.awt.font.TransformAttribute.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.font.TransformAttribute.IDENTITY;
        }
    },*/
    /**
     * java.awt.im.InputMethodHighlight
     */
    INPUTMETHODHIGHLIGHT(java.awt.im.InputMethodHighlight.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == java.awt.im.InputMethodHighlight.UNSELECTED_RAW_TEXT_HIGHLIGHT
                    || obj == java.awt.im.InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT
                    || obj == java.awt.im.InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT
                    || obj == java.awt.im.InputMethodHighlight.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
        }
    },
    /**
     * java.awt.im.InputSubset
     */
    INPUTSUBSET(java.awt.im.InputSubset.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.accessibility.AccessibleRole
     */
    ACCESSIBLEROLE(javax.accessibility.AccessibleRole.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == AccessibleRole.ALERT         || obj == AccessibleRole.COLUMN_HEADER || obj == AccessibleRole.CANVAS
                    || obj == AccessibleRole.COMBO_BOX || obj == AccessibleRole.DESKTOP_ICON /* || obj == AccessibleRole.HTML_CONTAINER */
                    || obj == AccessibleRole.INTERNAL_FRAME || obj == AccessibleRole.DESKTOP_PANE || obj == AccessibleRole.OPTION_PANE
                    || obj == AccessibleRole.WINDOW    || obj == AccessibleRole.FRAME         || obj == AccessibleRole.DIALOG
                    || obj == AccessibleRole.COLOR_CHOOSER || obj == AccessibleRole.DIRECTORY_PANE || obj == AccessibleRole.FILE_CHOOSER
                    || obj == AccessibleRole.FILLER    || obj == AccessibleRole.HYPERLINK     || obj == AccessibleRole.ICON
                    || obj == AccessibleRole.LABEL     || obj == AccessibleRole.ROOT_PANE     || obj == AccessibleRole.GLASS_PANE
                    || obj == AccessibleRole.LAYERED_PANE || obj == AccessibleRole.LIST       || obj == AccessibleRole.LIST_ITEM
                    || obj == AccessibleRole.MENU_BAR  || obj == AccessibleRole.POPUP_MENU    || obj == AccessibleRole.MENU
                    || obj == AccessibleRole.MENU_ITEM || obj == AccessibleRole.SEPARATOR     || obj == AccessibleRole.PAGE_TAB_LIST
                    || obj == AccessibleRole.PAGE_TAB  || obj == AccessibleRole.PANEL         || obj == AccessibleRole.PROGRESS_BAR
                    || obj == AccessibleRole.PASSWORD_TEXT || obj == AccessibleRole.PUSH_BUTTON || obj == AccessibleRole.TOGGLE_BUTTON
                    || obj == AccessibleRole.CHECK_BOX || obj == AccessibleRole.RADIO_BUTTON  || obj == AccessibleRole.ROW_HEADER
                    || obj == AccessibleRole.SCROLL_PANE || obj == AccessibleRole.SCROLL_BAR  || obj == AccessibleRole.VIEWPORT
                    || obj == AccessibleRole.SLIDER    || obj == AccessibleRole.SPLIT_PANE    || obj == AccessibleRole.TABLE
                    || obj == AccessibleRole.TEXT      || obj == AccessibleRole.TREE          || obj == AccessibleRole.TOOL_BAR
                    || obj == AccessibleRole.TOOL_TIP  || obj == AccessibleRole.AWT_COMPONENT || obj == AccessibleRole.SWING_COMPONENT
                    || obj == AccessibleRole.UNKNOWN   || obj == AccessibleRole.STATUS_BAR    || obj == AccessibleRole.DATE_EDITOR
                    || obj == AccessibleRole.SPIN_BOX  || obj == AccessibleRole.FONT_CHOOSER  || obj == AccessibleRole.GROUP_BOX
                    || obj == AccessibleRole.HEADER    || obj == AccessibleRole.FOOTER        || obj == AccessibleRole.PARAGRAPH
                    || obj == AccessibleRole.RULER     || obj == AccessibleRole.EDITBAR       || obj == AccessibleRole.PROGRESS_MONITOR;
        }
    },
    /**
     * javax.accessibility.AccessibleState
     */
    ACCESSIBLESTATE(javax.accessibility.AccessibleState.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == AccessibleState.ACTIVE         || obj == AccessibleState.PRESSED    || obj == AccessibleState.ARMED
                    || obj == AccessibleState.BUSY       || obj == AccessibleState.CHECKED    || obj == AccessibleState.EDITABLE
                    || obj == AccessibleState.EXPANDABLE || obj == AccessibleState.COLLAPSED  || obj == AccessibleState.EXPANDED
                    || obj == AccessibleState.ENABLED    || obj == AccessibleState.FOCUSABLE  || obj == AccessibleState.FOCUSED
                    || obj == AccessibleState.ICONIFIED  || obj == AccessibleState.MODAL      || obj == AccessibleState.OPAQUE
                    || obj == AccessibleState.RESIZABLE  || obj == AccessibleState.MULTISELECTABLE || obj == AccessibleState.SELECTABLE
                    || obj == AccessibleState.SELECTED   || obj == AccessibleState.SHOWING    || obj == AccessibleState.VISIBLE
                    || obj == AccessibleState.VERTICAL   || obj == AccessibleState.HORIZONTAL || obj == AccessibleState.SINGLE_LINE
                    || obj == AccessibleState.MULTI_LINE || obj == AccessibleState.TRANSIENT  || obj == AccessibleState.MANAGES_DESCENDANTS
                    || obj == AccessibleState.INDETERMINATE || obj == AccessibleState.TRUNCATED;
        }
    },
    /**
     * javax.crypto.spec.OAEPParameterSpec
     */
    OAEPPARAMETERSPEC(javax.crypto.spec.OAEPParameterSpec.class) {
        @Override
        boolean isShared(final Object obj) { return obj == javax.crypto.spec.OAEPParameterSpec.DEFAULT; }
    },
    /**
     * javax.crypto.spec.PSource.PSpecified
     */
    PSOURCE_PSPECIFIED(javax.crypto.spec.PSource.PSpecified.class) {
        @Override
        boolean isShared(final Object obj) { return obj == javax.crypto.spec.PSource.PSpecified.DEFAULT; }
    },
    /**
     * javax.imageio.plugins.jpeg.JPEGHuffmanTable
     */
    JPEGHUFFMANTABLE(javax.imageio.plugins.jpeg.JPEGHuffmanTable.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.imageio.plugins.jpeg.JPEGHuffmanTable.StdDCLuminance
                    || obj == javax.imageio.plugins.jpeg.JPEGHuffmanTable.StdDCChrominance
                    || obj == javax.imageio.plugins.jpeg.JPEGHuffmanTable.StdACLuminance
                    || obj == javax.imageio.plugins.jpeg.JPEGHuffmanTable.StdACChrominance;
        }
    },
    /**
     * javax.imageio.plugins.jpeg.JPEGQTable
     */
    JPEGQTABLE(javax.imageio.plugins.jpeg.JPEGQTable.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.imageio.plugins.jpeg.JPEGQTable.K1Luminance
                    || obj == javax.imageio.plugins.jpeg.JPEGQTable.K1Div2Luminance
                    || obj == javax.imageio.plugins.jpeg.JPEGQTable.K2Chrominance
                    || obj == javax.imageio.plugins.jpeg.JPEGQTable.K2Div2Chrominance;
        }
    },
    /**
     * javax.management.ImmutableDescriptor
     *//*
    IMMUTABLEDESCRIPTOR(javax.management.ImmutableDescriptor.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.management.ImmutableDescriptor.EMPTY_DESCRIPTOR;
        }
    },*/
    /**
     * javax.management.ObjectName
     *//*
    OBJECTNAME(javax.management.ObjectName.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.management.MBeanServerDelegate.DELEGATE_NAME
                    || obj == javax.management.ObjectName.WILDCARD;
        }
    },*/
    /**
     * javax.management.openmbean.SimpleType
     */
    SIMPLETYPE(javax.management.openmbean.SimpleType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.print.attribute.standard.MediaSize
     */
    MEDIASIZE(MediaSize.class) {
        @Override
        boolean isShared(final Object obj) {
            return GLOBAL_MEDIASIZES.contains(obj);
        }
    },
    /**
     * javax.print.DocFlavor.BYTE_ARRAY
     */
    DOCFLAVOR_BYTEARRAY(javax.print.DocFlavor.BYTE_ARRAY.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.BYTE_ARRAY.TEXT_PLAIN_HOST         || obj == DocFlavor.BYTE_ARRAY.TEXT_PLAIN_UTF_8
                    || obj == DocFlavor.BYTE_ARRAY.TEXT_PLAIN_UTF_16   || obj == DocFlavor.BYTE_ARRAY.TEXT_PLAIN_UTF_16BE
                    || obj == DocFlavor.BYTE_ARRAY.TEXT_PLAIN_UTF_16LE || obj == DocFlavor.BYTE_ARRAY.TEXT_PLAIN_US_ASCII
                    || obj == DocFlavor.BYTE_ARRAY.TEXT_HTML_HOST      || obj == DocFlavor.BYTE_ARRAY.TEXT_HTML_UTF_8
                    || obj == DocFlavor.BYTE_ARRAY.TEXT_HTML_UTF_16    || obj == DocFlavor.BYTE_ARRAY.TEXT_HTML_UTF_16BE
                    || obj == DocFlavor.BYTE_ARRAY.TEXT_HTML_UTF_16LE  || obj == DocFlavor.BYTE_ARRAY.TEXT_HTML_US_ASCII
                    || obj == DocFlavor.BYTE_ARRAY.PDF || obj == DocFlavor.BYTE_ARRAY.POSTSCRIPT || obj == DocFlavor.BYTE_ARRAY.PCL
                    || obj == DocFlavor.BYTE_ARRAY.GIF || obj == DocFlavor.BYTE_ARRAY.JPEG       || obj == DocFlavor.BYTE_ARRAY.PNG
                    || obj == DocFlavor.BYTE_ARRAY.AUTOSENSE;
        }
    },
    /**
     * javax.print.DocFlavor.INPUT_STREAM
     */
    DOCFLAVOR_INPUTSTREAM(javax.print.DocFlavor.INPUT_STREAM.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST         || obj == DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8
                    || obj == DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_16   || obj == DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_16BE
                    || obj == DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_16LE || obj == DocFlavor.INPUT_STREAM.TEXT_PLAIN_US_ASCII
                    || obj == DocFlavor.INPUT_STREAM.TEXT_HTML_HOST      || obj == DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8
                    || obj == DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16    || obj == DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16BE
                    || obj == DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_16LE  || obj == DocFlavor.INPUT_STREAM.TEXT_HTML_US_ASCII
                    || obj == DocFlavor.INPUT_STREAM.PDF || obj == DocFlavor.INPUT_STREAM.POSTSCRIPT || obj == DocFlavor.INPUT_STREAM.PCL
                    || obj == DocFlavor.INPUT_STREAM.GIF || obj == DocFlavor.INPUT_STREAM.JPEG       || obj == DocFlavor.INPUT_STREAM.PNG
                    || obj == DocFlavor.INPUT_STREAM.AUTOSENSE;
        }
    },
    /**
     * javax.print.DocFlavor.URL
     */
    DOCFLAVOR_URL(javax.print.DocFlavor.URL.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.URL.TEXT_PLAIN_HOST         || obj == DocFlavor.URL.TEXT_PLAIN_UTF_8
                    || obj == DocFlavor.URL.TEXT_PLAIN_UTF_16   || obj == DocFlavor.URL.TEXT_PLAIN_UTF_16BE
                    || obj == DocFlavor.URL.TEXT_PLAIN_UTF_16LE || obj == DocFlavor.URL.TEXT_PLAIN_US_ASCII
                    || obj == DocFlavor.URL.TEXT_HTML_HOST      || obj == DocFlavor.URL.TEXT_HTML_UTF_8
                    || obj == DocFlavor.URL.TEXT_HTML_UTF_16    || obj == DocFlavor.URL.TEXT_HTML_UTF_16BE
                    || obj == DocFlavor.URL.TEXT_HTML_UTF_16LE  || obj == DocFlavor.URL.TEXT_HTML_US_ASCII
                    || obj == DocFlavor.URL.PDF || obj == DocFlavor.URL.POSTSCRIPT || obj == DocFlavor.URL.PCL
                    || obj == DocFlavor.URL.GIF || obj == DocFlavor.URL.JPEG       || obj == DocFlavor.URL.PNG
                    || obj == DocFlavor.URL.AUTOSENSE;
        }
    },
    /**
     * javax.print.DocFlavor.CHAR_ARRAY
     */
    DOCFLAVOR_CHARARRAY(javax.print.DocFlavor.CHAR_ARRAY.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.CHAR_ARRAY.TEXT_PLAIN || obj == DocFlavor.CHAR_ARRAY.TEXT_HTML;
        }
    },
    /**
     * javax.print.DocFlavor.STRING
     */
    DOCFLAVOR_STRING(javax.print.DocFlavor.STRING.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.STRING.TEXT_PLAIN || obj == DocFlavor.STRING.TEXT_HTML;
        }
    },
    /**
     * javax.print.DocFlavor.READER
     */
    DOCFLAVOR_READER(javax.print.DocFlavor.READER.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.READER.TEXT_PLAIN || obj == DocFlavor.READER.TEXT_HTML;
        }
    },
    /**
     * javax.print.DocFlavor.SERVICE_FORMATTED
     */
    DOCFLAVOR_SERVICEFORMATTED(javax.print.DocFlavor.SERVICE_FORMATTED.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DocFlavor.SERVICE_FORMATTED.RENDERABLE_IMAGE
                    || obj == DocFlavor.SERVICE_FORMATTED.PRINTABLE
                    || obj == DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        }
    },
    /**
     * javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag
     */
    APPCONFIGURATIONENTRY_LOGINMODULECONTROLFLAG(javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.sound.midi.Sequencer.SyncMode
     */
    SEQUENCER_SYNCMODE(javax.sound.midi.Sequencer.SyncMode.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.midi.Sequencer.SyncMode.INTERNAL_CLOCK || obj == javax.sound.midi.Sequencer.SyncMode.MIDI_SYNC
                    || obj == javax.sound.midi.Sequencer.SyncMode.MIDI_TIME_CODE || obj == javax.sound.midi.Sequencer.SyncMode.NO_SYNC;
        }
    },
    /**
     * javax.sound.sampled.AudioFileFormat.Type
     */
    AUDIOFILEFORMAT_TYPE(javax.sound.sampled.AudioFileFormat.Type.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.sampled.AudioFileFormat.Type.WAVE || obj == javax.sound.sampled.AudioFileFormat.Type.AU
                    || obj == javax.sound.sampled.AudioFileFormat.Type.AIFF || obj == javax.sound.sampled.AudioFileFormat.Type.AIFC
                    || obj == javax.sound.sampled.AudioFileFormat.Type.SND;
        }
    },
    /**
     * javax.sound.sampled.BooleanControl.Type
     */
    BOOLEANCONTROL_TYPE(javax.sound.sampled.BooleanControl.Type.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.sampled.BooleanControl.Type.MUTE || obj == javax.sound.sampled.BooleanControl.Type.APPLY_REVERB;
        }
    },
    /**
     * javax.sound.sampled.EnumControl.Type
     */
    ENUMCONTROL_TYPE(javax.sound.sampled.EnumControl.Type.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.sampled.EnumControl.Type.REVERB;
        }
    },
    /**
     * javax.sound.sampled.FloatControl.Type
     */
    FLOATCONTROL_TYPE(javax.sound.sampled.FloatControl.Type.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.sampled.FloatControl.Type.MASTER_GAIN       || obj == javax.sound.sampled.FloatControl.Type.AUX_SEND
                    || obj == javax.sound.sampled.FloatControl.Type.AUX_RETURN    || obj == javax.sound.sampled.FloatControl.Type.REVERB_SEND
                    || obj == javax.sound.sampled.FloatControl.Type.REVERB_RETURN || obj == javax.sound.sampled.FloatControl.Type.VOLUME
                    || obj == javax.sound.sampled.FloatControl.Type.PAN           || obj == javax.sound.sampled.FloatControl.Type.BALANCE
                    || obj == javax.sound.sampled.FloatControl.Type.SAMPLE_RATE;
        }
    },
    /**
     * javax.sound.sampled.LineEvent.Type
     */
    LINEEVENT_TYPE(javax.sound.sampled.LineEvent.Type.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.sampled.LineEvent.Type.OPEN      || obj == javax.sound.sampled.LineEvent.Type.CLOSE
                    || obj == javax.sound.sampled.LineEvent.Type.START || obj == javax.sound.sampled.LineEvent.Type.STOP;
        }
    },
    /**
     * javax.sound.sampled.Port.Info
     */
    PORT_INFO(javax.sound.sampled.Port.Info.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.sound.sampled.Port.Info.MICROPHONE       || obj == javax.sound.sampled.Port.Info.LINE_IN
                    || obj == javax.sound.sampled.Port.Info.COMPACT_DISC || obj == javax.sound.sampled.Port.Info.SPEAKER
                    || obj == javax.sound.sampled.Port.Info.HEADPHONE    || obj == javax.sound.sampled.Port.Info.LINE_OUT;
        }
    },
    /**
     * javax.swing.event.DocumentEvent.EventType
     */
    DOCUMENTEVENT_EVENTTYPE(javax.swing.event.DocumentEvent.EventType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.event.HyperlinkEvent.EventType
     */
    HYPERLINKEVENT_EVENTTYPE(javax.swing.event.HyperlinkEvent.EventType.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.plaf.synth.ColorType
     */
    COLORTYPE(javax.swing.plaf.synth.ColorType.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == javax.swing.plaf.synth.ColorType.FOREGROUND          || obj == javax.swing.plaf.synth.ColorType.BACKGROUND
                    || obj == javax.swing.plaf.synth.ColorType.TEXT_FOREGROUND || obj == javax.swing.plaf.synth.ColorType.TEXT_BACKGROUND
                    || obj == javax.swing.plaf.synth.ColorType.FOCUS;
        }
    },
    /**
     * javax.swing.plaf.synth.Region
     */
    REGION(javax.swing.plaf.synth.Region.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == Region.ARROW_BUTTON || obj == Region.BUTTON || obj == Region.CHECK_BOX || obj == Region.CHECK_BOX_MENU_ITEM
                    || obj == Region.COLOR_CHOOSER || obj == Region.COMBO_BOX || obj == Region.DESKTOP_PANE || obj == Region.DESKTOP_ICON
                    || obj == Region.EDITOR_PANE || obj == Region.FILE_CHOOSER || obj == Region.FORMATTED_TEXT_FIELD
                    || obj == Region.INTERNAL_FRAME || obj == Region.INTERNAL_FRAME_TITLE_PANE || obj == Region.LABEL || obj == Region.LIST
                    || obj == Region.MENU || obj == Region.MENU_BAR || obj == Region.MENU_ITEM || obj == Region.MENU_ITEM_ACCELERATOR
                    || obj == Region.OPTION_PANE || obj == Region.PANEL || obj == Region.PASSWORD_FIELD || obj == Region.POPUP_MENU
                    || obj == Region.POPUP_MENU_SEPARATOR || obj == Region.PROGRESS_BAR || obj == Region.RADIO_BUTTON
                    || obj == Region.RADIO_BUTTON_MENU_ITEM || obj == Region.ROOT_PANE || obj == Region.SCROLL_BAR
                    || obj == Region.SCROLL_BAR_TRACK || obj == Region.SCROLL_BAR_THUMB || obj == Region.SCROLL_PANE
                    || obj == Region.SEPARATOR || obj == Region.SLIDER || obj == Region.SLIDER_TRACK || obj == Region.SLIDER_THUMB
                    || obj == Region.SPINNER || obj == Region.SPLIT_PANE || obj == Region.SPLIT_PANE_DIVIDER || obj == Region.TABBED_PANE
                    || obj == Region.TABBED_PANE_TAB || obj == Region.TABBED_PANE_TAB_AREA || obj == Region.TABBED_PANE_CONTENT
                    || obj == Region.TABLE || obj == Region.TABLE_HEADER || obj == Region.TEXT_AREA || obj == Region.TEXT_FIELD
                    || obj == Region.TEXT_PANE || obj == Region.TOGGLE_BUTTON || obj == Region.TOOL_BAR || obj == Region.TOOL_BAR_CONTENT
                    || obj == Region.TOOL_BAR_DRAG_WINDOW || obj == Region.TOOL_TIP || obj == Region.TOOL_BAR_SEPARATOR
                    || obj == Region.TREE || obj == Region.TREE_CELL || obj == Region.VIEWPORT;
        }
    },
    /**
     * javax.swing.text.Position.Bias
     */
    POSITION_BIAS(javax.swing.text.Position.Bias.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.html.CSS.Attribute
     */
    CSS_ATTRIBUTE(javax.swing.text.html.CSS.Attribute.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.html.HTML.Attribute
     */
    HTML_ATTRIBUTE(javax.swing.text.html.HTML.Attribute.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.StyleConstants
     */
    STYLECONSTANTS(javax.swing.text.StyleConstants.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.StyleConstants.ParagraphConstants
     */
    STYLECONSTANTS_PARAGRAPHCONSTANTS(javax.swing.text.StyleConstants.ParagraphConstants.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.StyleConstants.CharacterConstants
     */
    STYLECONSTANTS_CHARACTERCONSTANTS(javax.swing.text.StyleConstants.CharacterConstants.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.StyleConstants.ColorConstants
     */
    STYLECONSTANTS_COLORCONSTANTS(javax.swing.text.StyleConstants.ColorConstants.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.StyleConstants.FontConstants
     */
    STYLECONSTANTS_FONTCONSTANTS(javax.swing.text.StyleConstants.FontConstants.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.swing.text.html.HTML.Tag
     */
    HTML_TAG(javax.swing.text.html.HTML.Tag.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == HTML.Tag.A || obj == HTML.Tag.ADDRESS || obj == HTML.Tag.APPLET || obj == HTML.Tag.AREA || obj == HTML.Tag.B
                    || obj == HTML.Tag.BASE || obj == HTML.Tag.BASEFONT || obj == HTML.Tag.BIG || obj == HTML.Tag.BLOCKQUOTE
                    || obj == HTML.Tag.BODY || obj == HTML.Tag.BR || obj == HTML.Tag.CAPTION || obj == HTML.Tag.CENTER
                    || obj == HTML.Tag.CITE || obj == HTML.Tag.CODE || obj == HTML.Tag.COMMENT || obj == HTML.Tag.CONTENT
                    || obj == HTML.Tag.DD || obj == HTML.Tag.DFN || obj == HTML.Tag.DIR || obj == HTML.Tag.DIV || obj == HTML.Tag.DL
                    || obj == HTML.Tag.DT || obj == HTML.Tag.EM || obj == HTML.Tag.FONT || obj == HTML.Tag.FORM || obj == HTML.Tag.FRAME
                    || obj == HTML.Tag.FRAMESET || obj == HTML.Tag.H1 || obj == HTML.Tag.H2 || obj == HTML.Tag.H3 || obj == HTML.Tag.H4
                    || obj == HTML.Tag.H5 || obj == HTML.Tag.H6 || obj == HTML.Tag.HEAD || obj == HTML.Tag.HR || obj == HTML.Tag.HTML
                    || obj == HTML.Tag.I || obj == HTML.Tag.IMG || obj == HTML.Tag.IMPLIED || obj == HTML.Tag.INPUT
                    || obj == HTML.Tag.ISINDEX || obj == HTML.Tag.KBD || obj == HTML.Tag.LI || obj == HTML.Tag.LINK || obj == HTML.Tag.MAP
                    || obj == HTML.Tag.MENU || obj == HTML.Tag.META || obj == HTML.Tag.NOFRAMES || obj == HTML.Tag.OBJECT
                    || obj == HTML.Tag.OL || obj == HTML.Tag.OPTION || obj == HTML.Tag.P || obj == HTML.Tag.PARAM || obj == HTML.Tag.PRE
                    || obj == HTML.Tag.S || obj == HTML.Tag.SAMP || obj == HTML.Tag.SCRIPT || obj == HTML.Tag.SELECT
                    || obj == HTML.Tag.SMALL || obj == HTML.Tag.SPAN || obj == HTML.Tag.STRIKE || obj == HTML.Tag.STRONG
                    || obj == HTML.Tag.STYLE || obj == HTML.Tag.SUB || obj == HTML.Tag.SUP || obj == HTML.Tag.TABLE || obj == HTML.Tag.TD
                    || obj == HTML.Tag.TEXTAREA || obj == HTML.Tag.TH || obj == HTML.Tag.TITLE || obj == HTML.Tag.TR || obj == HTML.Tag.TT
                    || obj == HTML.Tag.U || obj == HTML.Tag.UL || obj == HTML.Tag.VAR || obj == HTML.getTag("nobr");
        }
    },
    /**
     * javax.xml.crypto.dsig.spec.XPathType.Filter
     *//*
    XPATHTYPE_FILTER(javax.xml.crypto.dsig.spec.XPathType.Filter.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },*/
    /**
     * javax.xml.crypto.KeySelector.Purpose
     *//*
    KEYSELECTOR_PURPOSE(javax.xml.crypto.KeySelector.Purpose.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },*/
    /**
     * javax.xml.datatype.DatatypeConstants.Field
     */
    DATATYPECONSTANTS_FIELD(DatatypeConstants.Field.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * javax.xml.namespace.QName
     */
    QNAME(javax.xml.namespace.QName.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == DatatypeConstants.DATETIME
                    || obj == DatatypeConstants.TIME
                    || obj == DatatypeConstants.DATE
                    || obj == DatatypeConstants.GYEARMONTH
                    || obj == DatatypeConstants.GMONTHDAY
                    || obj == DatatypeConstants.GYEAR
                    || obj == DatatypeConstants.GMONTH
                    || obj == DatatypeConstants.GDAY
                    || obj == DatatypeConstants.DURATION
                    || obj == DatatypeConstants.DURATION_DAYTIME
                    || obj == DatatypeConstants.DURATION_YEARMONTH
                    || obj == javax.xml.xpath.XPathConstants.NUMBER
                    || obj == javax.xml.xpath.XPathConstants.STRING
                    || obj == javax.xml.xpath.XPathConstants.BOOLEAN
                    || obj == javax.xml.xpath.XPathConstants.NODESET
                    || obj == javax.xml.xpath.XPathConstants.NODE
                    || obj == javax.xml.xpath.XPathConstants.DOM_OBJECT_MODEL;
            // Java 6:
//                    || obj == javax.xml.soap.SOAPConstants.SOAP_DATAENCODINGUNKNOWN_FAULT
//                    || obj == javax.xml.soap.SOAPConstants.SOAP_MUSTUNDERSTAND_FAULT
//                    || obj == javax.xml.soap.SOAPConstants.SOAP_RECEIVER_FAULT
//                    || obj == javax.xml.soap.SOAPConstants.SOAP_SENDER_FAULT
//                    || obj == javax.xml.soap.SOAPConstants.SOAP_VERSIONMISMATCH_FAULT;
        }
    },
    /**
     * org.omg.CORBA.CompletionStatus
     */
    COMPLETIONSTATUS(org.omg.CORBA.CompletionStatus.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * org.omg.CORBA.DefinitionKind
     */
    DEFINITIONKIND(org.omg.CORBA.DefinitionKind.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.CORBA.DefinitionKind.dk_none          || obj == org.omg.CORBA.DefinitionKind.dk_all
                    || obj == org.omg.CORBA.DefinitionKind.dk_Attribute || obj == org.omg.CORBA.DefinitionKind.dk_Constant
                    || obj == org.omg.CORBA.DefinitionKind.dk_Exception || obj == org.omg.CORBA.DefinitionKind.dk_Interface
                    || obj == org.omg.CORBA.DefinitionKind.dk_Module    || obj == org.omg.CORBA.DefinitionKind.dk_Operation
                    || obj == org.omg.CORBA.DefinitionKind.dk_Typedef   || obj == org.omg.CORBA.DefinitionKind.dk_Alias
                    || obj == org.omg.CORBA.DefinitionKind.dk_Struct    || obj == org.omg.CORBA.DefinitionKind.dk_Union
                    || obj == org.omg.CORBA.DefinitionKind.dk_Enum      || obj == org.omg.CORBA.DefinitionKind.dk_Primitive
                    || obj == org.omg.CORBA.DefinitionKind.dk_String    || obj == org.omg.CORBA.DefinitionKind.dk_Sequence
                    || obj == org.omg.CORBA.DefinitionKind.dk_Array     || obj == org.omg.CORBA.DefinitionKind.dk_Repository
                    || obj == org.omg.CORBA.DefinitionKind.dk_Wstring   || obj == org.omg.CORBA.DefinitionKind.dk_Fixed
                    || obj == org.omg.CORBA.DefinitionKind.dk_Value     || obj == org.omg.CORBA.DefinitionKind.dk_ValueBox
                    || obj == org.omg.CORBA.DefinitionKind.dk_ValueMember || obj == org.omg.CORBA.DefinitionKind.dk_Native
                    || obj == org.omg.CORBA.DefinitionKind.dk_AbstractInterface;
        }
    },
    /**
     * org.omg.CORBA.ParameterMode
     */
    PARAMETERMODE(org.omg.CORBA.ParameterMode.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.CORBA.ParameterMode.PARAM_IN
                    || obj == org.omg.CORBA.ParameterMode.PARAM_OUT
                    || obj == org.omg.CORBA.ParameterMode.PARAM_INOUT;
        }
    },
    /**
     * org.omg.CORBA.SetOverrideType
     */
    SETOVERRIDETYPE(org.omg.CORBA.SetOverrideType.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.CORBA.SetOverrideType.SET_OVERRIDE
                    || obj == org.omg.CORBA.SetOverrideType.ADD_OVERRIDE;
        }
    },
    /**
     * org.omg.CORBA.TCKind
     */
    TCKIND(org.omg.CORBA.TCKind.class) {
        @Override
        boolean isShared(final Object obj) { return true; }
    },
    /**
     * org.omg.CosNaming.BindingType
     */
    BINDINGTYPE(org.omg.CosNaming.BindingType.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.CosNaming.BindingType.nobject
                    || obj == org.omg.CosNaming.BindingType.ncontext;
        }
    },
    /**
     * org.omg.CosNaming.NamingContextPackage.NotFoundReason
     */
    NOTFOUNDREASON(org.omg.CosNaming.NamingContextPackage.NotFoundReason.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.CosNaming.NamingContextPackage.NotFoundReason.missing_node
                    || obj == org.omg.CosNaming.NamingContextPackage.NotFoundReason.not_context
                    || obj == org.omg.CosNaming.NamingContextPackage.NotFoundReason.not_object;
        }
    },
    /**
     * org.omg.PortableServer.IdAssignmentPolicyValue
     */
    IDASSIGNMENTPOLICYVALUE(org.omg.PortableServer.IdAssignmentPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID
                    || obj == org.omg.PortableServer.IdAssignmentPolicyValue.SYSTEM_ID;
        }
    },
    /**
     * org.omg.PortableServer.IdUniquenessPolicyValue
     */
    IDUNIQUENESSPOLICYVALUE(org.omg.PortableServer.IdUniquenessPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.IdUniquenessPolicyValue.UNIQUE_ID
                    || obj == org.omg.PortableServer.IdUniquenessPolicyValue.MULTIPLE_ID;
        }
    },
    /**
     * org.omg.PortableServer.ImplicitActivationPolicyValue
     */
    IMLICITACTIVATIONPOLICYVALUE(org.omg.PortableServer.ImplicitActivationPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.ImplicitActivationPolicyValue.IMPLICIT_ACTIVATION
                    || obj == org.omg.PortableServer.ImplicitActivationPolicyValue.NO_IMPLICIT_ACTIVATION;
        }
    },
    /**
     * org.omg.PortableServer.LifespanPolicyValue
     */
    LIFESPANPOLICYVALUE(org.omg.PortableServer.LifespanPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.LifespanPolicyValue.TRANSIENT
                    || obj == org.omg.PortableServer.LifespanPolicyValue.PERSISTENT;
        }
    },
    /**
     * org.omg.PortableServer.POAManagerPackage.State
     */
    STATE(org.omg.PortableServer.POAManagerPackage.State.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.POAManagerPackage.State.HOLDING
                    || obj == org.omg.PortableServer.POAManagerPackage.State.ACTIVE
                    || obj == org.omg.PortableServer.POAManagerPackage.State.DISCARDING
                    || obj == org.omg.PortableServer.POAManagerPackage.State.INACTIVE;
        }
    },
    /**
     * org.omg.PortableServer.RequestProcessingPolicyValue
     */
    REQUESTPROCESSINGPOLICYVALUE(org.omg.PortableServer.RequestProcessingPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.RequestProcessingPolicyValue.USE_ACTIVE_OBJECT_MAP_ONLY
                    || obj == org.omg.PortableServer.RequestProcessingPolicyValue.USE_DEFAULT_SERVANT
                    || obj == org.omg.PortableServer.RequestProcessingPolicyValue.USE_SERVANT_MANAGER;
        }
    },
    /**
     * org.omg.PortableServer.ServantRetentionPolicyValue
     */
    SERVANTRETENTIONPOLICYVALUE(org.omg.PortableServer.ServantRetentionPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.ServantRetentionPolicyValue.RETAIN
                    || obj == org.omg.PortableServer.ServantRetentionPolicyValue.NON_RETAIN;
        }
    },
    /**
     * org.omg.PortableServer.ThreadPolicyValue
     */
    THREADPOLICYVALUE(org.omg.PortableServer.ThreadPolicyValue.class) {
        @Override
        boolean isShared(final Object obj) {
            return obj == org.omg.PortableServer.ThreadPolicyValue.ORB_CTRL_MODEL
                    || obj == org.omg.PortableServer.ThreadPolicyValue.SINGLE_THREAD_MODEL;
        }
    },
    /**
     * misc comparisons that can not rely on the object's class.
     */
    MISC(Void.class) {
        @Override
        boolean isShared(final Object obj) {
            boolean emptyCollection = obj == Collections.EMPTY_SET || obj == Collections.EMPTY_LIST || obj == Collections.EMPTY_MAP
                    /*|| obj == Collections.emptyIterator() || obj == Collections.emptyEnumeration()*/ || obj == javax.swing.tree.DefaultMutableTreeNode.EMPTY_ENUMERATION;
            boolean systemStream = obj == System.in || obj == System.out || obj == System.err;
            boolean renderingHints = obj == RenderingHints.VALUE_ANTIALIAS_ON || obj == RenderingHints.VALUE_ANTIALIAS_OFF || obj == RenderingHints.VALUE_ANTIALIAS_DEFAULT
                    || obj == RenderingHints.VALUE_RENDER_SPEED || obj == RenderingHints.VALUE_RENDER_QUALITY || obj == RenderingHints.VALUE_RENDER_DEFAULT
                    || obj == RenderingHints.VALUE_DITHER_DISABLE || obj == RenderingHints.VALUE_DITHER_ENABLE || obj == RenderingHints.VALUE_DITHER_DEFAULT
                    || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_ON || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_OFF || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT
//                    || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_GASP || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB || obj == RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR
                    || obj == RenderingHints.VALUE_FRACTIONALMETRICS_OFF || obj == RenderingHints.VALUE_FRACTIONALMETRICS_ON || obj == RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT
                    || obj == RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR || obj == RenderingHints.VALUE_INTERPOLATION_BILINEAR || obj == RenderingHints.VALUE_INTERPOLATION_BICUBIC
                    || obj == RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED || obj == RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY || obj == RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT
                    || obj == RenderingHints.VALUE_COLOR_RENDER_SPEED || obj == RenderingHints.VALUE_COLOR_RENDER_QUALITY || obj == RenderingHints.VALUE_COLOR_RENDER_DEFAULT
                    || obj == RenderingHints.VALUE_STROKE_DEFAULT || obj == RenderingHints.VALUE_STROKE_NORMALIZE || obj == RenderingHints.VALUE_STROKE_PURE;
//            boolean socketOptions = obj == java.net.StandardSocketOptions.SO_BROADCAST || obj == java.net.StandardSocketOptions.SO_KEEPALIVE || obj == java.net.StandardSocketOptions.SO_SNDBUF || obj == java.net.StandardSocketOptions.SO_RCVBUF || obj == java.net.StandardSocketOptions.SO_REUSEADDR || obj == java.net.StandardSocketOptions.SO_LINGER
//                    || obj == java.net.StandardSocketOptions.IP_TOS || obj == java.net.StandardSocketOptions.IP_MULTICAST_IF || obj == java.net.StandardSocketOptions.IP_MULTICAST_TTL || obj == java.net.StandardSocketOptions.IP_MULTICAST_LOOP
//                    || obj == java.net.StandardSocketOptions.TCP_NODELAY;
//            boolean charset = obj == java.nio.charset.StandardCharsets.US_ASCII || obj == java.nio.charset.StandardCharsets.ISO_8859_1 || obj == java.nio.charset.StandardCharsets.UTF_8 || obj == java.nio.charset.StandardCharsets.UTF_16BE || obj == java.nio.charset.StandardCharsets.UTF_16LE || obj == java.nio.charset.StandardCharsets.UTF_16;
//            boolean watchEventKinds = obj == java.nio.file.StandardWatchEventKinds.OVERFLOW || obj == java.nio.file.StandardWatchEventKinds.ENTRY_CREATE || obj == java.nio.file.StandardWatchEventKinds.ENTRY_DELETE || obj == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
            boolean misc = obj == String.CASE_INSENSITIVE_ORDER || obj == java.io.ObjectStreamClass.NO_FIELDS
                    /*|| obj == java.security.Policy.UNSUPPORTED_EMPTY_COLLECTION*//*|| obj == java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory*/
                    /*|| obj == java.util.ResourceBundle.Control.FORMAT_DEFAULT || obj == java.util.ResourceBundle.Control.FORMAT_CLASS || obj == java.util.ResourceBundle.Control.FORMAT_PROPERTIES*/
                    || obj == javax.swing.text.SimpleAttributeSet.EMPTY;
            return emptyCollection || systemStream || renderingHints || misc;
        }
    };


    private static final Map<Class<?>, FlyweightType> TYPE_MAPPINGS = new HashMap<Class<?>, FlyweightType>();
    static {
        for (FlyweightType type : FlyweightType.values()) {
          TYPE_MAPPINGS.put(type.clazz, type);
        }
    }

    /**
     * @param classToSearch
     * @param fieldType
     * @return all static fields values of type {@code fieldType} within class {@code classToSearch}
     */
    private static <T> Set<T> getAllFields(final Class<?> classToSearch, final Class<T> fieldType) {
        Map<T, Void> result = new IdentityHashMap<T, Void>();
        for (Field f : classToSearch.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && fieldType.equals(f.getType())) {
                try {
                    result.put((T) f.get(null), null);
                } catch (IllegalArgumentException e) {
                    continue;
                } catch (IllegalAccessException e) {
                    continue;
                }
            }
        }
        return result.keySet();
    }

    private static final Set<Locale> GLOBAL_LOCALES;
    private static final Set<javax.print.attribute.standard.MediaSize> GLOBAL_MEDIASIZES;
    static {
        GLOBAL_LOCALES = getAllFields(Locale.class, Locale.class);
        Set<MediaSize> allMediaSizes = new HashSet<MediaSize>();
        allMediaSizes.addAll(getAllFields(MediaSize.Engineering.class, MediaSize.class));
        allMediaSizes.addAll(getAllFields(MediaSize.ISO.class, MediaSize.class));
        allMediaSizes.addAll(getAllFields(MediaSize.JIS.class, MediaSize.class));
        allMediaSizes.addAll(getAllFields(MediaSize.NA.class, MediaSize.class));
        allMediaSizes.addAll(getAllFields(MediaSize.Other.class, MediaSize.class));
        GLOBAL_MEDIASIZES = allMediaSizes;
    }

    private final Class<?> clazz;

    private FlyweightType(final Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Whether this is a shared object
     * @param obj the object to check for
     * @return true, if shared
     */
    abstract boolean isShared(Object obj);

    /**
     * Will return the Flyweight enum instance for the flyweight Class, or null if type isn't flyweight
     * @param aClazz the class we need the FlyweightType instance for
     * @return the FlyweightType, or null
     */
    static FlyweightType getFlyweightType(final Class<?> aClazz) {
        if (aClazz.isEnum() || (aClazz.getSuperclass() != null && aClazz.getSuperclass().isEnum())) {
            return ENUM;
        } else if (javax.print.attribute.EnumSyntax.class.isAssignableFrom(aClazz)) {
            return ENUM_SYNTAX;
        } else {
            FlyweightType flyweightType = TYPE_MAPPINGS.get(aClazz);
            return flyweightType != null ? flyweightType : MISC;
        }
    }
}
