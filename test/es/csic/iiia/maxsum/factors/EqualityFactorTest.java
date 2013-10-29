/*
 * Software License Agreement (BSD License)
 *
 * Copyright 2013 Marc Pujol <mpujol@iiia.csic.es>.
 *
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 *
 *   Redistributions of source code must retain the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer in the documentation and/or other
 *   materials provided with the distribution.
 *
 *   Neither the name of IIIA-CSIC, Artificial Intelligence Research Institute
 *   nor the names of its contributors may be used to
 *   endorse or promote products derived from this
 *   software without specific prior written permission of
 *   IIIA-CSIC, Artificial Intelligence Research Institute
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package es.csic.iiia.maxsum.factors;

import es.csic.iiia.maxsum.Factor;
import es.csic.iiia.maxsum.MaxOperator;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link EqualityFactor} HOP by checking the computed messages against those computed
 * by a {@link StandardFactor}.
 *
 * @author Marc Pujol <mpujol@iiia.csic.es>
 */
public class EqualityFactorTest extends CrossFactorTestAbstract {

    @Override
    public Factor[] buildFactors(MaxOperator op, Factor[] neighbors) {
        return new Factor[]{
            buildSpecificFactor(op, neighbors),
            buildStandardFactor(op, neighbors),
        };
    }

    private Factor buildSpecificFactor(MaxOperator op, Factor[] neighbors) {
        EqualityFactor factor = new EqualityFactor();
        factor.setMaxOperator(op);
        link(factor, neighbors);
        return factor;
    }

    private Factor buildStandardFactor(MaxOperator op, Factor[] neighbors) {
        StandardFactor factor = new StandardFactor();
        factor.setMaxOperator(op);
        link(factor, neighbors);
        factor.setPotential(buildPotential(op, neighbors));
        return factor;
    }

    public double[] buildPotential(MaxOperator op, Factor[] neighbors) {
        final int nNeighbors = neighbors.length;

        // Initialize the cost/utilites array with "no goods"
        double[] values = new double[1 << nNeighbors];
        Arrays.fill(values, op.getWorstValue());

        // Now set the rows with either no or all variables active to "0"
        values[0] = 0;
        values[values.length-1] = 0;

        return values;
    }

}