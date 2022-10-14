package com.example.accountservice.web;


public class ParamResource {
    private Long since;
    private Long until;
    private Integer limit;

    public ParamResource(final Long since, final Long until, final Integer limit) {
        this.since = since;
        this.until = until;
        this.limit = limit;
    }

    public ParamResource() {
    }

    public Long getSince() {
        return this.since;
    }

    public void setSince(final Long since) {
        this.since = since;
    }

    public Long getUntil() {
        return this.until;
    }

    public void setUntil(final Long until) {
        this.until = until;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ParamResource)) {
            return false;
        } else {
            ParamResource other = (ParamResource) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$since = this.getSince();
                    Object other$since = other.getSince();
                    if (this$since == null) {
                        if (other$since == null) {
                            break label47;
                        }
                    } else if (this$since.equals(other$since)) {
                        break label47;
                    }

                    return false;
                }

                Object this$until = this.getUntil();
                Object other$until = other.getUntil();
                if (this$until == null) {
                    if (other$until != null) {
                        return false;
                    }
                } else if (!this$until.equals(other$until)) {
                    return false;
                }

                Object this$limit = this.getLimit();
                Object other$limit = other.getLimit();
                if (this$limit == null) {
                    if (other$limit != null) {
                        return false;
                    }
                } else if (!this$limit.equals(other$limit)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ParamResource;
    }

    public int hashCode() {
        int result = 1;
        Object $since = this.getSince();
        result = result * 59 + ($since == null ? 43 : $since.hashCode());
        Object $until = this.getUntil();
        result = result * 59 + ($until == null ? 43 : $until.hashCode());
        Object $limit = this.getLimit();
        result = result * 59 + ($limit == null ? 43 : $limit.hashCode());
        return result;
    }

    public String toString() {
        return "ParamResource(since=" + this.getSince() + ", until=" + this.getUntil() + ", limit=" + this.getLimit() + ")";
    }
}

