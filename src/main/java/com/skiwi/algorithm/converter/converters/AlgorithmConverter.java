
package com.skiwi.algorithm.converter.converters;

import java.util.List;

/**
 * Interface to be implemented by algorithm converters.
 *
 * @author Frank van Heeswijk
 */
public interface AlgorithmConverter {
    /**
     * Converts the input algorithm text to the output algorithm text.
     * 
     * @param inputLines The input algorithm text, split up in lines
     * @return  The output algorithm text
     */
    String convert(final List<CharSequence> inputLines);
}
