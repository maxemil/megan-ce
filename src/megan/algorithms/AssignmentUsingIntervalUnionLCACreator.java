/*
 *  Copyright (C) 2015 Daniel H. Huson
 *
 *  (Some files contain contributions from other authors, who are then mentioned separately.)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package megan.algorithms;

import jloda.util.ProgramProperties;
import megan.classification.Classification;
import megan.core.Document;

/**
 * create a coverage-base LCA assignment algorithm
 * Daniel Huson, 4.2017
 */
public class AssignmentUsingIntervalUnionLCACreator implements IAssignmentAlgorithmCreator {
    private final Document document;

    /**
     * constructor
     *
     * @param document
     */
    public AssignmentUsingIntervalUnionLCACreator(Document document, float topPercent) {
        this.document = document;
        if (ProgramProperties.get("use-segment-lca", false))
            System.err.println("Using segment-LCA algorithm for binning: " + Classification.Taxonomy);
        else
            System.err.println("Using max-coverage-LCA algorithm for binning: " + Classification.Taxonomy);
    }

    /**
     * creates an assignment algorithm
     *
     * @return assignment algorithm
     */
    @Override
    public IAssignmentAlgorithm createAssignmentAlgorithm() {
        if (ProgramProperties.get("use-segment-lca", false)) {
            return new AssignmentUsingSegmentLCA(document);
        } else
            return new AssignmentUsingIntervalUnionLCA(document);
    }
}