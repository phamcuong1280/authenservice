package com.example.accountservice.web;


public class PageParamResource {
    private ParamResource params;
    private String rawQuery;

    public PageParamResource(final ParamResource params, final String rawQuery) {
        this.params = params;
        this.rawQuery = rawQuery;
    }

    public PageParamResource() {
    }

    public ParamResource getParams() {
        return this.params;
    }

    public void setParams(final ParamResource params) {
        this.params = params;
    }

    public String getRawQuery() {
        return this.rawQuery;
    }

    public void setRawQuery(final String rawQuery) {
        this.rawQuery = rawQuery;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageParamResource)) {
            return false;
        } else {
            PageParamResource other = (PageParamResource) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$params = this.getParams();
                Object other$params = other.getParams();
                if (this$params == null) {
                    if (other$params != null) {
                        return false;
                    }
                } else if (!this$params.equals(other$params)) {
                    return false;
                }

                Object this$rawQuery = this.getRawQuery();
                Object other$rawQuery = other.getRawQuery();
                if (this$rawQuery == null) {
                    if (other$rawQuery != null) {
                        return false;
                    }
                } else if (!this$rawQuery.equals(other$rawQuery)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageParamResource;
    }

    public int hashCode() {
        int result = 1;
        Object $params = this.getParams();
        result = result * 59 + ($params == null ? 43 : $params.hashCode());
        Object $rawQuery = this.getRawQuery();
        result = result * 59 + ($rawQuery == null ? 43 : $rawQuery.hashCode());
        return result;
    }

    public String toString() {
        return "PageParamResource(params=" + this.getParams() + ", rawQuery=" + this.getRawQuery() + ")";
    }
}

