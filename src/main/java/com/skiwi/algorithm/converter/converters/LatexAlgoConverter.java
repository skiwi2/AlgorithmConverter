
package com.skiwi.algorithm.converter.converters;

import java.util.List;

/**
 *
 * @author Frank van Heeswijk
 */
public class LatexAlgoConverter implements AlgorithmConverter {
    @Override
    public String convert(final List<CharSequence> inputLines) {
        return String.join(System.lineSeparator(), inputLines);
    }
}
