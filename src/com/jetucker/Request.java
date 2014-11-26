// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/com/jetucker/Request.proto

package com.jetucker;

public final class Request {
  private Request() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code com.jetucker.RequestControlCode}
   */
  public enum RequestControlCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>REQUEST_AUTH = 1;</code>
     */
    REQUEST_AUTH(0, 1),
    /**
     * <code>REQUEST_FILE = 2;</code>
     */
    REQUEST_FILE(1, 2),
    /**
     * <code>REQUEST_FINISH = 3;</code>
     */
    REQUEST_FINISH(2, 3),
    ;

    /**
     * <code>REQUEST_AUTH = 1;</code>
     */
    public static final int REQUEST_AUTH_VALUE = 1;
    /**
     * <code>REQUEST_FILE = 2;</code>
     */
    public static final int REQUEST_FILE_VALUE = 2;
    /**
     * <code>REQUEST_FINISH = 3;</code>
     */
    public static final int REQUEST_FINISH_VALUE = 3;


    public final int getNumber() { return value; }

    public static RequestControlCode valueOf(int value) {
      switch (value) {
        case 1: return REQUEST_AUTH;
        case 2: return REQUEST_FILE;
        case 3: return REQUEST_FINISH;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<RequestControlCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<RequestControlCode>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<RequestControlCode>() {
            public RequestControlCode findValueByNumber(int number) {
              return RequestControlCode.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.jetucker.Request.getDescriptor().getEnumTypes().get(0);
    }

    private static final RequestControlCode[] VALUES = values();

    public static RequestControlCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private RequestControlCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.jetucker.RequestControlCode)
  }

  public interface ControlRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.jetucker.ControlRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 userId = 1;</code>
     */
    boolean hasUserId();
    /**
     * <code>required int64 userId = 1;</code>
     */
    long getUserId();

    /**
     * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
     */
    boolean hasControlCode();
    /**
     * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
     */
    com.jetucker.Request.RequestControlCode getControlCode();

    /**
     * <code>optional string fileName = 3;</code>
     */
    boolean hasFileName();
    /**
     * <code>optional string fileName = 3;</code>
     */
    java.lang.String getFileName();
    /**
     * <code>optional string fileName = 3;</code>
     */
    com.google.protobuf.ByteString
        getFileNameBytes();
  }
  /**
   * Protobuf type {@code com.jetucker.ControlRequest}
   */
  public static final class ControlRequest extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:com.jetucker.ControlRequest)
      ControlRequestOrBuilder {
    // Use ControlRequest.newBuilder() to construct.
    private ControlRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ControlRequest(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ControlRequest defaultInstance;
    public static ControlRequest getDefaultInstance() {
      return defaultInstance;
    }

    public ControlRequest getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ControlRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              userId_ = input.readInt64();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.jetucker.Request.RequestControlCode value = com.jetucker.Request.RequestControlCode.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                controlCode_ = value;
              }
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              fileName_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.jetucker.Request.internal_static_com_jetucker_ControlRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.jetucker.Request.internal_static_com_jetucker_ControlRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.jetucker.Request.ControlRequest.class, com.jetucker.Request.ControlRequest.Builder.class);
    }

    public static com.google.protobuf.Parser<ControlRequest> PARSER =
        new com.google.protobuf.AbstractParser<ControlRequest>() {
      public ControlRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ControlRequest(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ControlRequest> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int USERID_FIELD_NUMBER = 1;
    private long userId_;
    /**
     * <code>required int64 userId = 1;</code>
     */
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 userId = 1;</code>
     */
    public long getUserId() {
      return userId_;
    }

    public static final int CONTROLCODE_FIELD_NUMBER = 2;
    private com.jetucker.Request.RequestControlCode controlCode_;
    /**
     * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
     */
    public boolean hasControlCode() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
     */
    public com.jetucker.Request.RequestControlCode getControlCode() {
      return controlCode_;
    }

    public static final int FILENAME_FIELD_NUMBER = 3;
    private java.lang.Object fileName_;
    /**
     * <code>optional string fileName = 3;</code>
     */
    public boolean hasFileName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string fileName = 3;</code>
     */
    public java.lang.String getFileName() {
      java.lang.Object ref = fileName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          fileName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string fileName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getFileNameBytes() {
      java.lang.Object ref = fileName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fileName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      userId_ = 0L;
      controlCode_ = com.jetucker.Request.RequestControlCode.REQUEST_AUTH;
      fileName_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasControlCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, userId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, controlCode_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getFileNameBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, userId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, controlCode_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getFileNameBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.jetucker.Request.ControlRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.jetucker.Request.ControlRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.jetucker.Request.ControlRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.jetucker.Request.ControlRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.jetucker.Request.ControlRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.jetucker.ControlRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.jetucker.ControlRequest)
        com.jetucker.Request.ControlRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.jetucker.Request.internal_static_com_jetucker_ControlRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.jetucker.Request.internal_static_com_jetucker_ControlRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.jetucker.Request.ControlRequest.class, com.jetucker.Request.ControlRequest.Builder.class);
      }

      // Construct using com.jetucker.Request.ControlRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        userId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        controlCode_ = com.jetucker.Request.RequestControlCode.REQUEST_AUTH;
        bitField0_ = (bitField0_ & ~0x00000002);
        fileName_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.jetucker.Request.internal_static_com_jetucker_ControlRequest_descriptor;
      }

      public com.jetucker.Request.ControlRequest getDefaultInstanceForType() {
        return com.jetucker.Request.ControlRequest.getDefaultInstance();
      }

      public com.jetucker.Request.ControlRequest build() {
        com.jetucker.Request.ControlRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.jetucker.Request.ControlRequest buildPartial() {
        com.jetucker.Request.ControlRequest result = new com.jetucker.Request.ControlRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userId_ = userId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.controlCode_ = controlCode_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.fileName_ = fileName_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.jetucker.Request.ControlRequest) {
          return mergeFrom((com.jetucker.Request.ControlRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.jetucker.Request.ControlRequest other) {
        if (other == com.jetucker.Request.ControlRequest.getDefaultInstance()) return this;
        if (other.hasUserId()) {
          setUserId(other.getUserId());
        }
        if (other.hasControlCode()) {
          setControlCode(other.getControlCode());
        }
        if (other.hasFileName()) {
          bitField0_ |= 0x00000004;
          fileName_ = other.fileName_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserId()) {
          
          return false;
        }
        if (!hasControlCode()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.jetucker.Request.ControlRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.jetucker.Request.ControlRequest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long userId_ ;
      /**
       * <code>required int64 userId = 1;</code>
       */
      public boolean hasUserId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 userId = 1;</code>
       */
      public long getUserId() {
        return userId_;
      }
      /**
       * <code>required int64 userId = 1;</code>
       */
      public Builder setUserId(long value) {
        bitField0_ |= 0x00000001;
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 userId = 1;</code>
       */
      public Builder clearUserId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userId_ = 0L;
        onChanged();
        return this;
      }

      private com.jetucker.Request.RequestControlCode controlCode_ = com.jetucker.Request.RequestControlCode.REQUEST_AUTH;
      /**
       * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
       */
      public boolean hasControlCode() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
       */
      public com.jetucker.Request.RequestControlCode getControlCode() {
        return controlCode_;
      }
      /**
       * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
       */
      public Builder setControlCode(com.jetucker.Request.RequestControlCode value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        controlCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .com.jetucker.RequestControlCode controlCode = 2;</code>
       */
      public Builder clearControlCode() {
        bitField0_ = (bitField0_ & ~0x00000002);
        controlCode_ = com.jetucker.Request.RequestControlCode.REQUEST_AUTH;
        onChanged();
        return this;
      }

      private java.lang.Object fileName_ = "";
      /**
       * <code>optional string fileName = 3;</code>
       */
      public boolean hasFileName() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string fileName = 3;</code>
       */
      public java.lang.String getFileName() {
        java.lang.Object ref = fileName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            fileName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string fileName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getFileNameBytes() {
        java.lang.Object ref = fileName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          fileName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string fileName = 3;</code>
       */
      public Builder setFileName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        fileName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string fileName = 3;</code>
       */
      public Builder clearFileName() {
        bitField0_ = (bitField0_ & ~0x00000004);
        fileName_ = getDefaultInstance().getFileName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string fileName = 3;</code>
       */
      public Builder setFileNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        fileName_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.jetucker.ControlRequest)
    }

    static {
      defaultInstance = new ControlRequest(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.jetucker.ControlRequest)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_jetucker_ControlRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_jetucker_ControlRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036src/com/jetucker/Request.proto\022\014com.je" +
      "tucker\"i\n\016ControlRequest\022\016\n\006userId\030\001 \002(\003" +
      "\0225\n\013controlCode\030\002 \002(\0162 .com.jetucker.Req" +
      "uestControlCode\022\020\n\010fileName\030\003 \001(\t*L\n\022Req" +
      "uestControlCode\022\020\n\014REQUEST_AUTH\020\001\022\020\n\014REQ" +
      "UEST_FILE\020\002\022\022\n\016REQUEST_FINISH\020\003B\tB\007Reque" +
      "st"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_jetucker_ControlRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_jetucker_ControlRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_jetucker_ControlRequest_descriptor,
        new java.lang.String[] { "UserId", "ControlCode", "FileName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
