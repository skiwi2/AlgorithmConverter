
package com.skiwi.algorithm.converter.converters;

/**
 * Interface to be implemented by algorithm converters.
 *
 * @author Frank van Heeswijk
 */
public interface AlgorithmConverter {
    /**
     * Converts the input algorithm text to the output algorithm text.
     * 
     * @param input The input algorithm text
     * @return  The output algorithm text
     */
    String convert(final String input);
}
