package com.fixmatch.entity;

/**
 * JobStatus Enum - Defines the status of a job
 * 
 * - OPEN: Job is posted and waiting for providers
 * - IN_PROGRESS: Provider has accepted and is working on the job
 * - COMPLETED: Job is finished
 * - CANCELLED: Job was cancelled by client or provider
 */
public enum JobStatus {
    OPEN,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
