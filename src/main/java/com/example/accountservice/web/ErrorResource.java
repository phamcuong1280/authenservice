package com.example.accountservice.web;


public class ErrorResource extends Resource<Object> {
    public ErrorResource(int code, final String message) {
        super(message, code);
    }

    public String toString() {
        return "ErrorResource()";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ErrorResource)) {
            return false;
        } else {
            ErrorResource other = (ErrorResource) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return super.equals(o);
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorResource;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
