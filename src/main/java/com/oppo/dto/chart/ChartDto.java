package com.oppo.dto.chart;

import java.util.List;

/**
 * Created by JieChen on 2019/1/27.
 */
public class ChartDto {
    List<Double> incomeSum;
    List<Double> expendSum;
    List<Double> profitSum;
    List<String> monthList;

    public ChartDto(List<Double> incomeSum, List<Double> expendSum, List<Double> profitSum, List<String> monthList) {
        this.incomeSum = incomeSum;
        this.expendSum = expendSum;
        this.profitSum = profitSum;
        this.monthList = monthList;
    }

    public ChartDto() {
    }

    public List<String> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<String> monthList) {
        this.monthList = monthList;
    }

    public List<Double> getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(List<Double> incomeSum) {
        this.incomeSum = incomeSum;
    }

    public List<Double> getExpendSum() {
        return expendSum;
    }

    public void setExpendSum(List<Double> expendSum) {
        this.expendSum = expendSum;
    }

    public List<Double> getProfitSum() {
        return profitSum;
    }

    public void setProfitSum(List<Double> profitSum) {
        this.profitSum = profitSum;
    }
}
