package com.example.accountservice.web;


public class PaginationResource implements IPaginationResource {
    private PageParamResource prev;
    private PageParamResource next;

    public PaginationResource(final PageParamResource prev, final PageParamResource next) {
        this.prev = prev;
        this.next = next;
    }

    public PaginationResource() {
    }

    public PageParamResource getPrev() {
        return this.prev;
    }

    public void setPrev(final PageParamResource prev) {
        this.prev = prev;
    }

    public PageParamResource getNext() {
        return this.next;
    }

    public void setNext(final PageParamResource next) {
        this.next = next;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PaginationResource)) {
            return false;
        } else {
            PaginationResource other = (PaginationResource) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$prev = this.getPrev();
                Object other$prev = other.getPrev();
                if (this$prev == null) {
                    if (other$prev != null) {
                        return false;
                    }
                } else if (!this$prev.equals(other$prev)) {
                    return false;
                }

                Object this$next = this.getNext();
                Object other$next = other.getNext();
                if (this$next == null) {
                    if (other$next != null) {
                        return false;
                    }
                } else if (!this$next.equals(other$next)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaginationResource;
    }

    public int hashCode() {
        int result = 1;
        Object $prev = this.getPrev();
        result = result * 59 + ($prev == null ? 43 : $prev.hashCode());
        Object $next = this.getNext();
        result = result * 59 + ($next == null ? 43 : $next.hashCode());
        return result;
    }

    public String toString() {
        return "PaginationResource(prev=" + this.getPrev() + ", next=" + this.getNext() + ")";
    }
}

