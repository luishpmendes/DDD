package moa.classifiers.core.driftdetection;

import moa.options.FloatOption;
import moa.options.IntOption;

public class EarlyDriftDetectionMethod extends EDDM {
	private static final long serialVersionUID = 1L;

	/** Value that determines whether the warning level is triggered */
	public FloatOption warningOption = new FloatOption("warning", 'w', "Value that determines whether the warning level is triggered.", 0.95, 0.0, 1.0);

	/** Value that determines whether a drift is considered to be detected */
	public FloatOption outControlOption = new FloatOption("outControl", 'd', "Value that determines whether a drift is considered to be detected.", 0.90, 0.0, 1.0);

	/** The minimum number of instances before permitting detecting change */
    public IntOption minNumInstancesOption = new IntOption("minNumInstances", 'n', "The minimum number of instances before permitting detecting change.", 30, 0, Integer.MAX_VALUE);

    /** The minimum number of errors before permitting detecting change */
    public IntOption minNumErrorsOption = new IntOption("minNumErrors", 'e', "The minimum number of errors before permitting detecting change.", 30, 0, Integer.MAX_VALUE);

    private double m_numErrors;

    private int m_n;

    private int m_d;

    private int m_lastd;

    private double m_mean;

    private double m_stdTemp;

    private double m_m2smax;

    private int m_lastLevel;

    private void initialize() {
        this.m_n = 1;
        this.m_numErrors = 0;
        this.m_d = 0;
        this.m_lastd = 0;
        this.m_mean = 0.0;
        this.m_stdTemp = 0.0;
        this.m_m2smax = 0.0;
        this.m_lastLevel = DDM_INCONTROL_LEVEL;
    }

    @Override
    public int computeNextVal(boolean prediction) {
    	this.m_n++;
        if (prediction == false) {
        	this.m_numErrors += 1;
        	this.m_lastd = this.m_d;
        	this.m_d = this.m_n - 1;
            int distance = this.m_d - this.m_lastd;
            double oldmean = this.m_mean;
            this.m_mean = this.m_mean + ((double) distance - this.m_mean) / this.m_numErrors;
            this.m_stdTemp = this.m_stdTemp + (distance - this.m_mean) * (distance - oldmean);
            double std = Math.sqrt(this.m_stdTemp / this.m_numErrors);
            double m2s = this.m_mean + 2 * std;
            if (m2s > this.m_m2smax) {
                if (this.m_n > this.minNumInstancesOption.getValue()) {
                	this.m_m2smax = m2s;
                }
                this.m_lastLevel = DDM_INCONTROL_LEVEL;
            } else {
                double p = m2s / this.m_m2smax;
                if (this.m_n > this.minNumInstancesOption.getValue() && this.m_numErrors > this.minNumErrorsOption.getValue() && p < this.outControlOption.getValue()) {
                    initialize();
                    return DDM_OUTCONTROL_LEVEL;
                } else if (this.m_n > this.minNumInstancesOption.getValue() && this.m_numErrors > this.minNumErrorsOption.getValue() && p < this.warningOption.getValue()) {
                    this.m_lastLevel = DDM_WARNING_LEVEL;
                    return DDM_WARNING_LEVEL;
                } else {
                    this.m_lastLevel = DDM_INCONTROL_LEVEL;
                    return DDM_INCONTROL_LEVEL;
                }
            }
        }
        return this.m_lastLevel;
    }
}
